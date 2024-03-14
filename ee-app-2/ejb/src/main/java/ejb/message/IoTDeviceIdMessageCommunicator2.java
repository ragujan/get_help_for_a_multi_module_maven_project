package ejb.message;

import core.DeviceManagerUtil;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "getIoTDeviceIds"),
        }
)
public class IoTDeviceIdMessageCommunicator2 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println(message);
        System.out.println("object is printed out");

        try {
//            String msg = message.getBody(String.class);
//            System.out.println("message is " + msg);
            InitialContext context = new InitialContext();
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) context.lookup("iotDeviceManagerQueueConnectionFactory");
            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            QueueSession queueSession = queueConnection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);

            Queue queue = (Queue) context.lookup("sendIoTDeviceIds");
            jakarta.jms.QueueSender sender = queueSession.createSender(queue);

            DeviceManagerUtil deviceManagerUtil = new DeviceManagerUtil();
            ObjectMessage objectMessage = queueSession.createObjectMessage((Serializable) deviceManagerUtil.getIoTDevices());
            Map<String,Object> dataMap = new HashMap<>();

            dataMap.put("ids",deviceManagerUtil.getIoTDevices());

            objectMessage.setObject((Serializable) dataMap);
//            TextMessage textMessage = queueSession.createTextMessage();
//            textMessage.setText("Message from the server");


//            sender.send(textMessage);
            sender.send(objectMessage);


            queueConnection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
}
