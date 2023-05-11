package com.lizard.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * @author X
 * @version 1.0
 * @since 2023-05-03 17:31
 **/
@Service
public class LoggerHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoggerHandler.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void info(String message, Object... args) {
        logger.info(message, args);
        String sessionUser = "test";
        messagingTemplate.convertAndSendToUser(sessionUser,"/queue/log", message);
    }
}
