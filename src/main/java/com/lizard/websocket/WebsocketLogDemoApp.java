package com.lizard.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

/**
 * 开启消息代理功能
 * 开启异步功能
 *
 * @author X
 */
@SpringBootApplication
@EnableWebSocketMessageBroker
public class WebsocketLogDemoApp {
    public static void main(String[] args) {
        SpringApplication.run(WebsocketLogDemoApp.class, args);
    }
}
