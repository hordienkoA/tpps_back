package ua.cn.stu.tpps.buyfly.dao;

import ua.cn.stu.tpps.buyfly.domain.User;

import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * DAO layer interface to work with the class {@link ua.cn.stu.tpps.buyfly.domain.User}.
 * Extends a generic interface, adding to it additional functions.
 */
public interface UserDao extends GenericDao<User> {

    /**
     * Set user status to enabled or disabled.
     *
     * @param enabled enable or disable user
     * @throws PersistenceException if error occurs
     */
    void setEnabled(boolean enabled, Integer id) throws PersistenceException;


    /**
     * Get collection of users by role.
     *
     * @param role role name
     * @return collection of users
     * @throws PersistenceException if error occurs
     */
    Collection<User> getByRoleType(String role) throws PersistenceException;


    /**
     * Get collection of users registered for specified flight
     *
     * @param flightId flight unique identifier
     * @return collection of users
     * @throws PersistenceException if error occurs
     */
    Collection<User> getByFlight(Integer flightId) throws PersistenceException;

    User getByEmail(String email) throws PersistenceException;
}
