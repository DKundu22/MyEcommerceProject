package com.ecommerce.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);

    public static void startTestSuite(String message) {
        logger.info("=== START SUITE === " + message);
    }

    public static void endTestSuite(String message) {
        logger.info("=== END SUITE === " + message);
    }
    
    public static void startTestCase(String testCaseName) {
        logger.info("---- STARTING TEST CASE: " + testCaseName + " ----");
    }

    public static void endTestCase(String testCaseName) {
        logger.info("---- ENDING TEST CASE: " + testCaseName + " ----");
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable t) {
        logger.error(message, t);
    }
}

