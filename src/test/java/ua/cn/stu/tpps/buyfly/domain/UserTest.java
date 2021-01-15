package ua.cn.stu.tpps.buyfly.domain;

import org.junit.Before;
import org.junit.Test;
import ua.cn.stu.tpps.buyfly.values.Sex;
import ua.cn.stu.tpps.buyfly.values.UserRole;

import static org.junit.Assert.assertEquals;


public class UserTest {
    private User admin;

    @Before
    public void initData() {
        admin = new User();
        admin.setUserRole(UserRole.ADMIN);
        admin.setSex(Sex.MALE);
    }

    @Test
    public void userRoleTest() throws Exception {
        assertEquals(UserRole.ADMIN.toString(), admin.getUserRole());
    }

    @Test
    public void userSexTest() throws Exception {
        assertEquals(Sex.MALE.toString(), admin.getSex());
    }
}