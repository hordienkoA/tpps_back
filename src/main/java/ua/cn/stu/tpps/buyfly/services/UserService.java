package ua.cn.stu.tpps.buyfly.services;

import ua.cn.stu.tpps.buyfly.domain.User;

import java.util.Collection;

public interface UserService extends GenericService<User> {
    boolean checkExists(User user);

    void setEnabled(Integer userId, boolean enabled);

    void sendConfirmationEmail(User user);

    Collection<User> getByRoleType(String role);

    Collection<User> getByFlight(Integer flightId);

    User getByEmail(String email);
}
