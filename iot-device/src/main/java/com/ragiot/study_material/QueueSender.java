package com.ragiot.study_material;

import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.LinkedList;


public class QueueSender {
    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext context = new InitialContext();

        QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup("myQueueConnectionFactory");
        QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
//        queueConnection.start();
//        QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
//      client acknowledgement is set, client is responsible for confirming that the message is received from the server
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
        queueConnection.close();



        queueConnection.start();

        queue = (Queue) context.lookup("sendIoTDeviceIds");

        jakarta.jms.QueueReceiver  receiver = queueSession.createReceiver(queue);

        receiver.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    LinkedList<String> ids = message.getBody(LinkedList.class);
                    ids.forEach(e -> System.out.println(e));
//                    String t  = message.getBody(String.class);
//                    System.out.println(t);

                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        while(true){

        }

//       use IoT ejb bean

//        context.lookup("java:global/ear/app/")



    }
}
