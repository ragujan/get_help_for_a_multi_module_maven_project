package com.module.web.servlet;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/analytics-live")
public class WebSocketTest {
    @OnOpen
    public void open(Session session) {

    }

    @OnClose
    public void close(Session session) {

    }
    @OnError
    public void onError(Throwable error){

    }
    @OnMessage
    public void handleMessage(String message,Session session){

    }
}
