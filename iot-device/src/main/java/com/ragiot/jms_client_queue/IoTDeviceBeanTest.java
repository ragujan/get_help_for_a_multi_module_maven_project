package com.ragiot.jms_client_queue;

import com.ragiot.utils.IoTDataGenerator;
import core.IoTDeviceReadingState;
import ejb.remote.IoTDeviceReadingStore;
import ejb.remote.Tax;
import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;


public class IoTDeviceBeanTest {
    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext context = new InitialContext();

        QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup("myQueueConnectionFactory");
        QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
        queueConnection.start();

        QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

//      client acknowledgement is set, client is responsible for confirming that the message is received from the server
//        QueueSession queueSession = queueConnection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);

        Queue queue = (Queue) context.lookup("myQueue");

        jakarta.jms.QueueSender sender = queueSession.createSender(queue);
//
//        TextMessage message = queueSession.createTextMessage();
//        for (int i = 0; i < 10; i++) {
//            message.setText("Bro this is a queue message " + i);
//            sender.send(message);
////            client acknowledging that the message is received.
//            message.acknowledge();
//        }
//        topicConnection.close();

//       use IoT ejb bean

//        context.lookup("java:global/ear/app/")
//        Tax tax = (Tax) context.lookup("java:global/ear/app/TaxCalculateBean");
//        tax.setValue(500);

        IoTDeviceReadingState state = new IoTDeviceReadingState();
        String lightStatus = IoTDataGenerator.randomStatus();
        int speed = IoTDataGenerator.randomSpeed();
        state.captureTrafficLightStatus(lightStatus);
        state.captureVehicleSpeed(speed);

        IoTDeviceReadingStore readingStoreBean = (IoTDeviceReadingStore) context.lookup("java:global/ear/app/IoTDeviceReadingStoreBean");
        readingStoreBean.setName("hey");
        readingStoreBean.setReading(state);
        ObjectMessage message = queueSession.createObjectMessage();
        message.setObject(readingStoreBean);
        sender.send(message);



//        readingStoreBean.set
//        System.out.println(context.lookup("java:global/ear-1.0/com.rag.ee-app-ejb-1.0/IoTDeviceReadingBean"));
//
//        reading.captureVehicleSpeed(100);
//        reading.captureTrafficLightStatus("yellow");

//        IoTDeviceReadingStoreBean storeBean = (IoTDeviceReadingStoreBean) context.lookup("java:/global/ear/app/IoTDeviceReadingStoreBean");
//        storeBean.setReading(reading);

        System.out.println("hey");


    }
}
