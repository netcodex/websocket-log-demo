package com.lizard.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author X
 * @version 1.0
 * @since 2023-05-03 16:47
 **/
@Configuration
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue");
        registry.setApplicationDestinationPrefixes("/log");
    }

    /**
     * 配置WebSocket消息代理端点，即stomp服务端
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 为了连接安全，setAllowedOrigins设置的允许连接的源地址，如果在非这个配置的地址下发起连接会报403，进一步还可以使用addInterceptors设置拦截器，来做相关的鉴权操作
        registry.addEndpoint("/websocket").setAllowedOriginPatterns("*").addInterceptors(new WebsocketLogHandSakeInterceptor()).setHandshakeHandler(new WebsocketLogHandSakeHandler()).withSockJS();
    }
}
