package ua.cn.stu.tpps.buyfly.services.implemented;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.cn.stu.tpps.buyfly.dao.GenericDao;
import ua.cn.stu.tpps.buyfly.domain.DomainSuperClass;
import ua.cn.stu.tpps.buyfly.exceptions.ServiceException;
import ua.cn.stu.tpps.buyfly.services.GenericService;
import ua.cn.stu.tpps.buyfly.util.ResourceMessage;

import java.util.Collection;

import static ua.cn.stu.tpps.buyfly.values.ResourceStringsDictionary.*;

/**
 * Implements all the methods of the interface {@link GenericService}, using
 * methods of {@link GenericDao} for operations with dao.
 * This class defines several methods that must be implemented in subclasses. These are methods that update
 * object with new information and initialize the interface {@link GenericDao}.
 *
 * @param <T> class of object to work with
 */
public abstract class GenericServiceImpl<T extends DomainSuperClass, TDao extends GenericDao<T>> implements GenericService<T> {

    private static final Logger logger = LogManager.getLogger("BuyFly");

    private static final String BUNDLE_EXCEPTION_MESSAGES = "errormessages";

    // Interface to work with
    protected TDao dao;

    public GenericServiceImpl() {
        super();
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.services.GenericService#save(java.lang.Object)
     */
    @Override
    public Integer save(T entity) throws ServiceException {
        try {
            Integer newEntity = dao.save(entity);

            if (newEntity != null) {
                return newEntity;
            } else {
                String exceptionText = ResourceMessage
                    .get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_FAILED_TO_ADD, entity.getClass().getSimpleName());

                logger.error(exceptionText);
                throw new ServiceException(exceptionText);
            }
        } catch (Exception e) {
            String exceptionText = ResourceMessage
                .get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_FAILED_TO_ADD, entity.getClass().getSimpleName());

            logger.error(exceptionText, e);
            throw new ServiceException(exceptionText, e);
        }
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.services.GenericService#remove(Integer)
     */
    @Override
    public void remove(Integer id) throws ServiceException {
        try {
            dao.remove(id);
        } catch (Exception e) {
            String exceptionText = ResourceMessage
                .get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_FAILED_TO_REMOVE, id);

            logger.error(exceptionText, e);
            throw new ServiceException(exceptionText, e);
        }
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.services.GenericService#getAll()
     */
    @Override
    public Collection<T> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (Exception e) {
            String exceptionText = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_FAILED_TO_GET_ALL);

            logger.error(exceptionText, e);
            throw new ServiceException(exceptionText, e);
        }
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.services.GenericService#getById(java.lang.Integer)
     */
    @Override
    public T getById(Integer id) throws ServiceException {
        try {
            T savedEntity = dao.getById(id);

            if ((savedEntity == null) || (savedEntity.getId() == null)) {
                String exceptionText = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_INCORRECT_ID, id);

                logger.error(exceptionText);
                throw new ServiceException(exceptionText);
            }

            return savedEntity;
        } catch (Exception e) {
            String exceptionText = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, ENTITY_FAILED_TO_GET_BY_ID, id);

            logger.error(exceptionText, e);
            throw new ServiceException(exceptionText, e);
        }
    }
}
