package com.ragiot.jms_client_queue;

import com.ragiot.utils.IoTDataGenerator;
import core.IoTDeviceReadingState;
import core.IoTDeviceReadingStoreBeanDTO;
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
        Queue queue = (Queue) context.lookup("myQueue");
        jakarta.jms.QueueSender sender = queueSession.createSender(queue);

        IoTDeviceReadingState state = new IoTDeviceReadingState();
        String lightStatus = IoTDataGenerator.randomStatus();
        int speed = IoTDataGenerator.randomSpeed();
        state.captureTrafficLightStatus(lightStatus);
        state.captureVehicleSpeed(speed);

        IoTDeviceReadingState state2 = new IoTDeviceReadingState();
        String lightStatus2 = IoTDataGenerator.randomStatus();
        int speed2 = IoTDataGenerator.randomSpeed();
        state2.captureTrafficLightStatus(lightStatus2);
        state2.captureVehicleSpeed(speed2);


        IoTDeviceReadingStore readingStoreBean = (IoTDeviceReadingStore) context.lookup("java:global/ear/app/IoTDeviceReadingStoreBean");

        readingStoreBean.setName("hey");
        readingStoreBean.setReading(state);
        readingStoreBean.setReading(state2);
        System.out.println(readingStoreBean.getName());
        IoTDeviceReadingStoreBeanDTO dto = null;
        dto = readingStoreBean.getDTO();

        ObjectMessage message = queueSession.createObjectMessage();
        message.setObject(dto);
        sender.send(message);

        System.out.println("hey");


    }
}
