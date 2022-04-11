package com.picpayclone.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilLog {

    private static final Logger logger =
            LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());

    // Methods
    public static void error(String message) {
        logger.error(message);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warning(String message) {
        logger.warn(message);
    }

    public static void error(Exception exception) {
        exception.printStackTrace();
    }
}
