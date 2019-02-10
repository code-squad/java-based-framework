package util;

public class StringConverter {

    public static String extractMethodName(String str) {
        return str.substring(3, 4).toLowerCase() + str.substring(4);
    }

    public static String createMethod(String prefix, String name) {
        return prefix + name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
