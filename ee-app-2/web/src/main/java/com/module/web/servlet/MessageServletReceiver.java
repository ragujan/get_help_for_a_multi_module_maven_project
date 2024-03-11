package com.module.web.servlet;


import jakarta.annotation.Resource;
import jakarta.jms.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(name = "MessageServletReceiver", value = "/message-servlet-receiver")
public class MessageServletReceiver extends HttpServlet {
    @Resource(lookup = "myQueueConnectionFactory")
    private QueueConnectionFactory connectionFactory;

    @Resource(lookup = "myQueue")
    private Queue queue ;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            InitialContext initialContext = new InitialContext();
//            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) initialContext.lookup("myQueueConnectionFactory");
            try {
                QueueConnection connection = connectionFactory.createQueueConnection();
                QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

//                Queue queue = (Queue) initialContext.lookup("myQueue");
                Queue queue = (Queue) initialContext.lookup("myQueue");

                jakarta.jms.QueueReceiver receiver = session.createReceiver(queue);

                receiver.setMessageListener(new MessageListener() {
                    @Override
                    public void onMessage(Message message) {
                        try {
                            String t = message.getBody(String.class);
                            response.getWriter().write(t);
                        } catch (JMSException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

                while(true){

                }

            } catch (JMSException e) {
                throw new RuntimeException(e);
            }

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
}
