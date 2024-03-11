package com.rag.jms_client;

import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Publisher {
    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext context = new InitialContext();

        TopicConnectionFactory myTopicConnectionFactory =(TopicConnectionFactory) context.lookup("myTopicConnectionFactory");
        TopicConnection topicConnection = myTopicConnectionFactory.createTopicConnection();
        TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = (Topic) context.lookup("myJmsTopicConnection") ;


       TopicPublisher publisher = topicSession.createPublisher(topic);

       TextMessage message = topicSession.createTextMessage();
       message.setText("Hello HI HI HI");

        publisher.publish(message);



    }
}
