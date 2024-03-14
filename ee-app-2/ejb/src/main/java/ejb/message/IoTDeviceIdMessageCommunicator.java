package ejb.message;

import core.DeviceManagerUtil;
import ejb.impl.IoTDeviceManagerBean;
import ejb.remote.IoTDeviceCount;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

//@MessageDriven(
//        activationConfig = {
//                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "getIoTDeviceIds"),
//        }
//)
public class IoTDeviceIdMessageCommunicator implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println(message);
        System.out.println("object is printed out");

        try {
            String msg = message.getBody(String.class);
            System.out.println("message is " + msg);
            DeviceManagerUtil deviceManagerUtil = new DeviceManagerUtil();
            List<String> ids = deviceManagerUtil.getIoTDevices();
            ids.forEach(e -> System.out.println(e));


        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
