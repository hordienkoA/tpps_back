package ua.cn.stu.tpps.buyfly.services.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.cn.stu.tpps.buyfly.dao.UserDao;
import ua.cn.stu.tpps.buyfly.domain.User;
import ua.cn.stu.tpps.buyfly.services.UserService;

import java.util.Collection;

/**
 * Implementation of UserService
 */
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User, UserDao> implements UserService {

    @Autowired
    protected void setDao(UserDao userDao) {
        dao = userDao;
    }

    @Override
    public User getByEmail(String email) {
        return dao.getByEmail(email);
    }

    @Override
    public boolean checkExists(User user) {
        //TODO Override equals() for class User
        User savedUser = dao.getById(user.getId());
        return savedUser != null && savedUser.equals(user);
    }

    @Override
    public void setEnabled(Integer userId, boolean enabled) {
        dao.setEnabled(enabled, userId);
    }

    @Override
    public void sendConfirmationEmail(User user) {
        //TODO Implement it
    }

    @Override
    public Collection<User> getByRoleType(String role) {
        return dao.getByRoleType(role);
    }

    @Override
    public Collection<User> getByFlight(Integer flightId) {
        return dao.getByFlight(flightId);
    }
}
