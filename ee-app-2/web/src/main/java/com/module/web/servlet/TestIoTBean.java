package com.module.web.servlet;

//import ejb.remote.IoTDeviceReading;

import core.IoTDeviceReadingState;
import ejb.remote.IoTDeviceReadingStore;
import jakarta.jms.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.Serializable;


@WebServlet(name = "TestIoTBean", value = "/test-iot-bean")
public class TestIoTBean extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InitialContext context = null;
        try {
            context = new InitialContext();

            IoTDeviceReadingState state = new IoTDeviceReadingState();
            String lightStatus = "green";
            int speed = 100;
            state.captureTrafficLightStatus(lightStatus);
            state.captureVehicleSpeed(speed);

            IoTDeviceReadingStore readingStoreBean = (IoTDeviceReadingStore) context.lookup("java:global/ear/app/IoTDeviceReadingStoreBean");
            readingStoreBean.setReading(state);

            response.getWriter().write(readingStoreBean.getReadings().getCapturedTrafficLightStatus());


            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup("myQueueConnectionFactory");
            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

//      client acknowledgement is set, client is responsible for confirming that the message is received from the server
//        QueueSession queueSession = queueConnection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);

            Queue queue = (Queue) context.lookup("myQueue");

            jakarta.jms.QueueSender sender = queueSession.createSender(queue);
            ObjectMessage message = queueSession.createObjectMessage();
            message.setObject((Serializable) readingStoreBean);
            sender.send(message);


        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
