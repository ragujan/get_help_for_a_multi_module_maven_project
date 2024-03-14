package com.ragiot.study_material;

import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueReceiver {
    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext context = new InitialContext();

        QueueConnectionFactory queueConnectionFactory =(QueueConnectionFactory) context.lookup("myQueueConnectionFactory");
        QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
        queueConnection.start();

        QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);


        Queue queue = (Queue) context.lookup("myQueue");

        jakarta.jms.QueueReceiver receiver = queueSession.createReceiver(queue);

         receiver.setMessageListener(new MessageListener() {
             @Override
             public void onMessage(Message message) {
                 try {
                     String t = message.getBody(String.class);
                     System.out.println("msg is "+t);
                 } catch (JMSException e) {
                     throw new RuntimeException(e);
                 }
             }
         });

         while(true){

         }

    }
}
