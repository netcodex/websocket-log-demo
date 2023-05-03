package com.lizard.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author X
 * @version 1.0
 * @since 2023-05-03 17:31
 **/
public class LoggerDelegate {
    private static final Logger logger = LoggerFactory.getLogger(LoggerDelegate.class);

    public static void info(String message, Object... args) {
        logger.info(message, args);
    }

    public static void error(String message, Object... args) {
        logger.error(message, args);
    }
}
