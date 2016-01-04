package com.home.it.jdbc.utils;

public class LoggingUtil {

    public LoggingUtil() {}

    public static String getCurrentClassName() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            return e.getStackTrace()[1].getClassName();
        }
    }
}
