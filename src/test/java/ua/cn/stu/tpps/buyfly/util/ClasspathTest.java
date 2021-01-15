package ua.cn.stu.tpps.buyfly.util;

import org.junit.Test;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Print out classpath
 */
public class ClasspathTest {
    @Test
    public void testClasspath() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) cl).getURLs();

        for (URL url : urls) {
            System.out.println(url.getFile());
        }
    }

}
