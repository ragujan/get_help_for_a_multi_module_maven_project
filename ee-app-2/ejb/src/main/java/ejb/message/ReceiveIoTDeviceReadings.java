package ejb.message;

import core.DeviceManagerUtil;
import core.IoTDeviceReadingStoreBeanDTO;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "transmitIoTReadings"),
        }
)
public class ReceiveIoTDeviceReadings implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("object is printed out");
        try {

            InitialContext context = new InitialContext();
            IoTDeviceReadingStoreBeanDTO dto = message.getBody(IoTDeviceReadingStoreBeanDTO.class);
            System.out.println(dto.getId());
            System.out.println(dto.getReadings().getCapturedGPSCoordinates().getLatitude());


        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
}
