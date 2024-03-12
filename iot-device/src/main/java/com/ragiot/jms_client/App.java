package com.ragiot.jms_client;

import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class App {
    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext context = new InitialContext();

        TopicConnectionFactory myTopicConnectionFactory =(TopicConnectionFactory) context.lookup("myTopicConnectionFactory");
        TopicConnection topicConnection = myTopicConnectionFactory.createTopicConnection();
        topicConnection.start();

        TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);


        Topic topic = (Topic) context.lookup("myJmsTopicConnection") ;

        TopicSubscriber topicSubscriber = topicSession.createSubscriber(topic);

        System.out.println(topicSubscriber.getTopic().getTopicName());
        topicSubscriber.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                String n = null;
                try {
                    n = message.getBody(String.class);
                    System.out.println(n);
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
//                System.out.println(n);
            }
        });

        while(true){

        }
//        topicConnection.close();

    }
}
