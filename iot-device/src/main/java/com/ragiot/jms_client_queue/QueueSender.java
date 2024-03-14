package com.ragiot.jms_client_queue;

import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class QueueSender {
    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext context = new InitialContext();

        QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup("myQueueConnectionFactory");
        QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
        QueueSession queueSession = queueConnection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);


        Queue queue = (Queue) context.lookup("getIoTDeviceIds");

        jakarta.jms.QueueSender sender = queueSession.createSender(queue);

//        TextMessage message = queueSession.createTextMessage();
//        message.setText("you from the device manager");
//        sender.send(message);
//        for (int i = 0; i < 10; i++) {
//            message.setText("Bro this is a queue message " + i);
//            sender.send(message);
////            client acknowledging that the message is received.
//            message.acknowledge();
//        }
        ObjectMessage objectMessage = queueSession.createObjectMessage();
        objectMessage.setObject(new LinkedList<String>());
        sender.send(objectMessage);

        queueSession.close();
        queueConnection.close();


        queueConnectionFactory = (QueueConnectionFactory) context.lookup("iotDeviceManagerQueueConnectionFactory");
        queueConnection = queueConnectionFactory.createQueueConnection();
        queueSession = queueConnection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);
        queueConnection.start();

        queue = (Queue) context.lookup("sendIoTDeviceIds");

        jakarta.jms.QueueReceiver receiver = queueSession.createReceiver(queue);

        receiver.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
//                    String t = message.getBody(String.class);
//                    System.out.println(t);
                    Map<String,Object> dataMap = (Map<String, Object>) message.getBody(Map.class);

                    for (Map.Entry<String,Object> entry:dataMap.entrySet()){
                        String key = entry.getKey();
                        System.out.println(key);
                    }
                    List<String> data = (List<String>) dataMap.get("ids");

//                    ids.forEach(e -> System.out.println(e));
//                    String t  = message.getBody(String.class);
//                    System.out.println(t);

                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        while (true) {

        }

//       use IoT ejb bean

//        context.lookup("java:global/ear/app/")


    }
}
