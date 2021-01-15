package ua.cn.stu.tpps.buyfly.dao.implJpa;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.cn.stu.tpps.buyfly.dao.GenericDao;
import ua.cn.stu.tpps.buyfly.domain.DomainSuperClass;
import ua.cn.stu.tpps.buyfly.util.HibernateUtil;
import ua.cn.stu.tpps.buyfly.util.ResourceMessage;

import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.List;

import static ua.cn.stu.tpps.buyfly.values.ResourceStringsDictionary.*;


/**
 * Implements all methods of interface {@link GenericDao} for communication with database,
 * using JPA.
 *
 * @param <T> class of objects that are involved in operations with the database.
 */
public class GenericDaoJpa<T extends DomainSuperClass> implements GenericDao<T> {

    private static final String BUNDLE_EXCEPTION_MESSAGES = "errormessages";

    // Query string to find all objects
    private static final String QUERY_SELECT_ALL = "SELECT x FROM %s x";

    // Persistent class that this dao works with
    private Class<T> persistentClass;

    /**
     * @param persistentClass class that this DAO will work with
     */
    public GenericDaoJpa(Class<T> persistentClass) {
        super();
        this.persistentClass = persistentClass;
    }


    /**
     * (non-Javadoc)
     *
     * @see GenericDao#save(DomainSuperClass)
     */
    @Override
    public Integer save(T entity) throws PersistenceException {
        if (entity == null) {
            throw new PersistenceException(ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_CANNOT_BE_NULL_SAVING));
        }

        T savedEntity;

        //TODO move to Spring as an aspect
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Creating new entity, if id is null or merging the detached object with the current state
            if (entity.getId() == null) {
                session.persist(entity);
                savedEntity = entity;
            } else {
                //noinspection unchecked
                savedEntity = (T) session.merge(entity);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new PersistenceException(e);
        }

        return savedEntity.getId();
    }


    /**
     * (non-Javadoc)
     *
     * @see GenericDao#remove(Integer)
     */
    @Override
    public void remove(Integer id) throws PersistenceException {
        if (id == null) {
            throw new PersistenceException(ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_CANNOT_BE_NULL_REMOVING));
        }

        //TODO move to Spring as an aspect
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            T savedEntity = session.get(persistentClass, id);
            session.delete(savedEntity);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new PersistenceException(e);
        }
    }


    /**
     * (non-Javadoc)
     *
     * @see GenericDao#getAll()
     */
    @Override
    public Collection<T> getAll() throws PersistenceException {
        return executeQuery(String.format(QUERY_SELECT_ALL, persistentClass.getSimpleName()), false, false);
    }


    /**
     * (non-Javadoc)
     *
     * @see GenericDao#getById(Integer)
     */
    @Override
    public T getById(Integer id) throws PersistenceException {
        if (id == null) {
            throw new PersistenceException(ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_CANNOT_BE_NULL_GETTING));
        }

        T savedEntity;

        //TODO move to Spring as an aspect
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            savedEntity = session.get(persistentClass, id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new PersistenceException(e);
        }

        return savedEntity;
    }


    /**
     * <p>
     * This template method executes query with performing all needed
     * operations, like creating EntityManager, creating transaction,
     * committing, or rolling it back.
     * </p>
     * <p>
     * Methods sets parameters for the query as they appear in the parameters
     * list, by number starting from 1. So, the first parameter in your named
     * query should be referenced as <code>?1</code>, second as <code>?2</code>
     * and so on.
     * </p>
     * <p>
     * If <code>isSingleResult = true</code> and no result is found, then
     * <code>null</code> is returned.
     * </p>
     * <p>
     * Be aware, that when multiple results are returned, they are being
     * dynamically casted to <code>R</code> class. It should be able to cast
     * to {@link java.util.List}. When returning single result, <code>R</code>
     * should be a single persistent entity class.
     * </p>
     *
     * @param queryOrQueryName query string or NamedQuery name
     * @param isNamedQuery     specifies, whether query is named query
     * @param isSingleResult   specifies, whether single result should be returned
     * @param parameters       parameters. You can specify multiple parameters separated by comma
     * @param <R>              class of the result
     * @return Result of the query
     * @throws PersistenceException if error occurs
     */
    @SuppressWarnings("unchecked")
    protected <R> R executeQuery(String queryOrQueryName, boolean isNamedQuery, boolean isSingleResult, Object... parameters)
        throws PersistenceException {

        Transaction transaction = null;

        R result;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            org.hibernate.query.Query query;

            // Creating either named or simple query
            if (isNamedQuery) {
                query = session.getNamedQuery(queryOrQueryName);
            } else {
                query = session.createQuery(queryOrQueryName);
            }

            // Setting query parameters
            for (int param = 0; param < parameters.length; param++) {
                query.setParameter(param + 1, parameters[param]);
            }

            //Execute query
            if (isSingleResult) {
                List<?> list = query.list();
                if (!list.isEmpty()) {
                    result = (R) list.get(0);
                } else {
                    result = null;
                }
            } else {
                result = (R) query.list();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new PersistenceException(e);
        }

        return result;
    }

}
