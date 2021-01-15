package ua.cn.stu.tpps.buyfly.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility class to work with localized strings from resource bundles.
 */
public class ResourceMessage {

    public ResourceMessage() {
    }


    /**
     * Gets a string for the given key from the given resource bundle or one of its parents.
     *
     * @param baseName the base name of the resource bundle, a fully qualified class name
     * @param string   resource string to value from
     * @param params   optional parameters passed to string
     * @return the string for the given key
     */
    public static String get(String baseName, String string, Object... params) {
        return getMessage(baseName, null, null, string, params);
    }


    /**
     * Gets a string for the given key from the given resource bundle or one of its parents.
     *
     * @param baseName the base name of the resource bundle, a fully qualified class name
     * @param locale   the locale for which a resource bundle is desired
     * @param string   resource string to value from
     * @param params   optional parameters passed to string
     * @return the string for the given key
     */
    public static String get(String baseName, Locale locale, String string, Object... params) {
        return getMessage(baseName, locale, null, string, params);
    }


    /**
     * Gets a string for the given key from the given resource bundle or one of its parents.
     *
     * @param baseName the base name of the resource bundle, a fully qualified class name
     * @param locale   the locale for which a resource bundle is desired
     * @param loader   the class loader from which to load the resource bundle
     * @param string   resource string to value from
     * @param params   optional parameters passed to string
     * @return the string for the given key
     */
    public static String get(String baseName, Locale locale, ClassLoader loader, String string, Object... params) {
        return getMessage(baseName, locale, loader, string, params);
    }


    /**
     * internally used to get a string for the given key from this resource bundle or one of its parents.
     *
     * @param baseName the base name of the resource bundle, a fully qualified class name
     * @param locale   the locale for which a resource bundle is desired
     * @param loader   the class loader from which to load the resource bundle
     * @param string   resource string to value from
     * @param params   optional parameters passed to string
     * @return the string for the given key
     */
    private static String getMessage(String baseName, Locale locale, ClassLoader loader, String string, Object... params) {
        if (baseName == null || string == null || string.isEmpty()) {
            return "Some error occurred";
        }

        ResourceBundle bundle;

        if (locale != null) {
            if (loader != null) {
                bundle = ResourceBundle.getBundle(baseName, locale, loader);
            } else {
                bundle = ResourceBundle.getBundle(baseName, locale);
            }
        } else {
            bundle = ResourceBundle.getBundle(baseName);
        }

        String message = bundle.getString(string);

        if (params != null) {
            message = MessageFormat.format(message, params);
        }

        return message;
    }
}
