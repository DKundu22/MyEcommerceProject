package com.ecommerce.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);

    public static void startTestSuite(String message) {
        logger.info("=== START SUITE === " + message);
    }

    public static void endTestSuite(String message) {
        logger.info("=== END SUITE === " + message);

        // Archive log file at the end of the suite
        try {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            Path source = Paths.get("logs/automation.log");
            Path archiveDir = Paths.get("logs/archive");
            Path target = archiveDir.resolve("automation-" + timestamp + ".log");

            // Create archive directory if it doesn't exist
            if (!Files.exists(archiveDir)) {
                Files.createDirectories(archiveDir);
            }

            // Copy current log to archive
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            logger.info("Archived log file to: " + target.toString());
        } catch (IOException e) {
            logger.error("Failed to archive automation.log", e);
        }
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

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable t) {
        logger.error(message, t);
    }
}
