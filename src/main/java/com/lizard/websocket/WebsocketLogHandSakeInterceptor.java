package com.lizard.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.security.Principal;
import java.text.MessageFormat;
import java.util.Map;

/**
 * @author X
 * @version 1.0
 * @since 2023-05-11 21:35
 **/
public class WebsocketLogHandSakeInterceptor implements HandshakeInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(WebsocketLogHandSakeInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
        Map<String, Object> attributes) throws Exception {

        HttpSession session = null;
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request;
            URI uri = servletRequest.getURI();
            System.out.println("uri = " + uri);
            HttpServletRequest httpRequest = servletRequest.getServletRequest();
            StringBuffer requestURL = httpRequest.getRequestURL();
            System.out.println("requestURL = " + requestURL);
            String servletPath = httpRequest.getServletPath();
            System.out.println("servletPath = " + servletPath);
            Principal userPrincipal = httpRequest.getUserPrincipal();
            System.out.println("userPrincipal = " + userPrincipal);
            String requestedSessionId = httpRequest.getRequestedSessionId();
            System.out.println("requestedSessionId = " + requestedSessionId);
            session = httpRequest.getSession(false);
        }

        Object loginUser = "test";

        if (loginUser != null) {
            logger.debug(MessageFormat.format("用户{0}请求建立WebSocket连接", loginUser));
            return true;
        } else {
            logger.error("未登录系统，禁止连接WebSocket");
            return false;
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
        Exception exception) {

    }
}
