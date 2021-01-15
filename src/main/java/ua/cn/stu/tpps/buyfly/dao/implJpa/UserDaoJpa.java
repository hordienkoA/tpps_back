package ua.cn.stu.tpps.buyfly.dao.implJpa;

import ua.cn.stu.tpps.buyfly.dao.UserDao;
import ua.cn.stu.tpps.buyfly.domain.User;

import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * Implements User DAO.
 */
public class UserDaoJpa extends GenericDaoJpa<User> implements UserDao {

    public UserDaoJpa() {
        super(User.class);
    }

    @Override
    public User getByEmail(String email) throws PersistenceException {
        return executeQuery("getByEmail", true, true, email);
    }

    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.UserDao#setEnabled(boolean, Integer)
     */
    @Override
    public void setEnabled(boolean enabled, Integer id) throws PersistenceException {
        int enabledDBVal = enabled ? 1 : 0;
        executeQuery("setEnabled", true, true, enabledDBVal, id);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.UserDao#getByRoleType(java.lang.String)
     */
    @Override
    public Collection<User> getByRoleType(String role) throws PersistenceException {
        return executeQuery("getByRoleType", true, false, role);
    }


    /**
     * (non-Javadoc)
     *
     * @see ua.cn.stu.tpps.buyfly.dao.UserDao#getByFlight(java.lang.Integer)
     */
    @Override
    public Collection<User> getByFlight(Integer flightId) throws PersistenceException {
        return executeQuery("getForFlight", true, false, flightId);
    }
}
