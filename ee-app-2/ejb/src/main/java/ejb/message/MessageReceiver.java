package ejb.message;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

//@MessageDriven(
//        activationConfig = {
//                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "myQueue")
//        }
//)
public class MessageReceiver implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            String t = message.getBody(String.class);
            System.out.println("message is "+t);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
