package com.module.web.websocket;

import ejb.impl.ClientSessionHandlerBean;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.StringReader;

@ApplicationScoped
@ServerEndpoint("/analytics-live")
public class WebSocketTest {

//    @Inject
    @EJB
    private ClientSessionHandlerBean sessionHandler;

    @OnOpen
    public void open(Session session) {
        System.out.println("hey bro ");
        sessionHandler.addSession(session);
        System.out.println("device count "+sessionHandler.getDevices().size());;
    }

    @OnClose
    public void close(Session session) {
//        sessionHandler.removeSession(session);
        System.out.println("session is removed");
    }

    @OnError
    public void onError(Throwable error) {

        error.printStackTrace();
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        System.out.println("message is being handled");
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();
//            sessionHandler.sendTextMessage("Yo this is rag");
        }
    }
}
