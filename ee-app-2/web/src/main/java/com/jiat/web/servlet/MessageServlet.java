package com.rag.web.servlet;


import jakarta.annotation.Resource;
import jakarta.jms.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(name = "MessageServlet", value = "/message-servlet")
public class MessageServlet extends HttpServlet {
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
                QueueSender sender = session.createSender(queue);

                TextMessage message = session.createTextMessage();

                for (int i = 0; i <10 ; i++) {
                    message.setText("queue message "+i);
                    sender.send(message);
                }
                connection.close();

            } catch (JMSException e) {
                throw new RuntimeException(e);
            }

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        response.getWriter().write("hey");
    }
}
