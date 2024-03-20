package ejb.impl;

import core.model.Device;
import jakarta.ejb.Local;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.json.JsonObject;
import jakarta.json.spi.JsonProvider;
import jakarta.websocket.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Startup
@Local
@Singleton
public class ClientSessionHandlerBean {
    private int deviceId = 0;
    private final Set<Session> sessions = new HashSet<>();
    private final Set<Device> devices = new HashSet<>();

    public void addSession(Session session) {
        sessions.add(session);
//        for (Device device:devices){
//            JsonObject addMessage = createAddMessage(device);
//        }
    }

    public void sendTextMessage(String message) {
        if(sessions == null || sessions.size()==0){
            return;
        }
        for (Session session : sessions) {
            JsonProvider provider = JsonProvider.provider();
            JsonObject jsonObject = provider.createObjectBuilder().add("message", message).build();
            sendToSession(session, jsonObject);
        }
    }
    public void sendObjects(JsonObject jsonObject) {
        if(sessions == null || sessions.size()==0){
            return;
        }
        for (Session session : sessions) {
            JsonProvider provider = JsonProvider.provider();
            sendToSession(session, jsonObject);
        }
    }
    public List<Device> getDevices() {
        return new ArrayList<>(devices);
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(ClientSessionHandlerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Device getDeviceById(int id) {
        for (Device device : devices) {
            if (device.getId() == id) {
                return device;
            }
        }
        return null;
    }

}
