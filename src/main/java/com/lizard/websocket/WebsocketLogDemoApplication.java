package com.lizard.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 开启消息代理功能
 * 开启异步功能
 *
 * @author X
 */
@SpringBootApplication
@EnableWebSocketMessageBroker
public class WebsocketLogDemoApplication {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public static void main(String[] args) {
        SpringApplication.run(WebsocketLogDemoApplication.class, args);
    }

    @PostConstruct
    public void publishWebsocketLog() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            while (true) {
                try {
                    LoggerMessage message = LoggerQueue.getInstance().poll();
                    if (message != null && messagingTemplate != null) {
                        messagingTemplate.convertAndSend("/topic/app", message);
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
