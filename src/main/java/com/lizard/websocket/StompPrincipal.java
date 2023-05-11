package com.lizard.websocket;

import java.security.Principal;

/**
 * @author X
 * @version 1.0
 * @since 2023-05-12 00:19
 **/
public class StompPrincipal implements Principal {
    private String name;

    public StompPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
