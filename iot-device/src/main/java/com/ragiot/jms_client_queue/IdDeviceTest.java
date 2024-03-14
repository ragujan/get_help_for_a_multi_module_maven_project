package com.ragiot.jms_client_queue;

import ejb.remote.IoTDeviceManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

public class IdDeviceTest {

    public static List<String> totalDevices() {
        InitialContext context = null;
        try {
            context = new InitialContext();
            IoTDeviceManager manager = (IoTDeviceManager) context.lookup("java:global/ear/app/IoTDeviceManagerBean");

            List<String> ids = manager.getDeviceIds();
            ids.forEach(e -> System.out.println(e));

            return ids;
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
