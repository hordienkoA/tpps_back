package ua.cn.stu.tpps.buyfly.util;

import org.junit.Test;

import java.util.Locale;

/**
 * Test utility for getting string message from resource bundles.
 */
public class ResourceMessageTest {
    private static final String BUNDLE_EXCEPTION_MESSAGES = "testmessages";

    @Test
    public void getWithOneArgTest() throws Exception {
        String msg = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, "testString.args1", 701);
        System.out.println("1 param: " + msg);
    }

    @Test
    public void getWithTwoArgsTest() throws Exception {
        String msg = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, "testString.args2", 701, "qwerty");
        System.out.println("2 params: " + msg);
    }

    @Test
    public void getWithThreeArgsTest() throws Exception {
        String msg = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, "testString.args3", 701, "qwerty", 42);
        System.out.println("3 params: " + msg);
    }

    @Test
    public void getWithoutArgsTest() throws Exception {
        String msg = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, "testString.noargs");
        System.out.println("No params: " + msg);
    }

    @Test
    public void getNoBundleTest() throws Exception {
        String msg = ResourceMessage.get(null, "testString.noargs");
        System.out.println("No bundle: " + msg);
    }

    @Test
    public void getNoStringTest() throws Exception {
        String msg = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, null);
        System.out.println("No string: " + msg);
    }

    @Test
    public void getLocaleTest() throws Exception {
        Locale locale = new Locale("ru_RU");
        String msg = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, locale, "testString.noargs");
        System.out.println("Locale: " + msg);
    }

    @Test
    public void getLocaleWithArgsTest() throws Exception {
        Locale locale = new Locale("ru_RU");
        String msg = ResourceMessage.get(BUNDLE_EXCEPTION_MESSAGES, locale, "testString.args2", 701, "qwerty");
        System.out.println("Locale RU with args: " + msg);
    }

}