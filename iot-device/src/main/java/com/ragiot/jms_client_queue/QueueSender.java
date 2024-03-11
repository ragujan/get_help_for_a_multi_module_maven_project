package com.ragiot.jms_client_queue;

import ejb.remote.IoTDeviceReading;
import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;


public class QueueSender {
    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext context = new InitialContext();

        QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup("myQueueConnectionFactory");
        QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
//        queueConnection.start();

//        QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);


//      client acknowledgement is set, client is responsible for confirming that the message is received from the server
        QueueSession queueSession = queueConnection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);


        Queue queue = (Queue) context.lookup("myQueue");

        jakarta.jms.QueueSender sender = queueSession.createSender(queue);

        TextMessage message = queueSession.createTextMessage();
//        for (int i = 0; i < 10; i++) {
//            message.setText("Bro this is a queue message " + i);
//            sender.send(message);
////            client acknowledging that the message is received.
//            message.acknowledge();
//        }
        System.out.println("hey");
//        topicConnection.close();

//       use IoT ejb bean

//        context.lookup("java:global/ear/app/")
        IoTDeviceReading reading =(IoTDeviceReading) context.lookup("java:/global/ear/app/IotDeviceReading");

        reading.captureVehicleSpeed(100);
        reading.captureTrafficLightStatus("yellow");




        queueConnection.close();

    }
}
