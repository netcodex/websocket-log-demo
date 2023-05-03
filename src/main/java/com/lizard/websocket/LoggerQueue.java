package com.lizard.websocket;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author X
 * @version 1.0
 * @since 2023-05-03 15:03
 **/
public class LoggerQueue {
    private static final LoggerQueue loggerQueue = new LoggerQueue();

    private final BlockingQueue<LoggerMessage> messageBlockingQueue = new ArrayBlockingQueue<>(100);

    private LoggerQueue(){

    }

    public static LoggerQueue getInstance() {
        return loggerQueue;
    }

    public boolean push(LoggerMessage loggerMessage) {
       return this.messageBlockingQueue.add(loggerMessage);
    }

    public LoggerMessage poll() {
        try {
            return this.messageBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
