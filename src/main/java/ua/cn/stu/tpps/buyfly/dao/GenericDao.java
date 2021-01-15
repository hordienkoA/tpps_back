package ua.cn.stu.tpps.buyfly.dao;

import ua.cn.stu.tpps.buyfly.domain.DomainSuperClass;

import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * Defines common methods for all dao interfaces
 *
 * @param <T> The class of objects that are involved in operations with the database
 * @author Alexander Mahdybor
 */
public interface GenericDao<T extends DomainSuperClass> {

    /**
     * Save new object to database or update existing.
     *
     * @param entity object to save
     * @return the processed object ID
     * @throws PersistenceException if error occurs
     */
    Integer save(T entity) throws PersistenceException;


    /**
     * Removes object from database.
     *
     * @param id object to remove unique identifier
     * @throws PersistenceException if error occurs
     */
    void remove(Integer id) throws PersistenceException;


    /**
     * Get collection of all objects.
     *
     * @return collection of all objects
     * @throws PersistenceException if error occurs
     */
    Collection<T> getAll() throws PersistenceException;


    /**
     * Find object by its unique identifier.
     *
     * @param id unique object identifier
     * @return found object or <code>null</code>
     * @throws PersistenceException if error occurs
     */
    T getById(Integer id) throws PersistenceException;
}
