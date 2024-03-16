package ejb.message;

import core.IoTDeviceReadingStoreBeanDTO;
import ejb.impl.DbConnectionBean;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "transmitIoTReadings"),
        }
)
public class ReceiveIoTDeviceReadings implements MessageListener {
    @EJB
    DbConnectionBean dbConnectionBean;
    @Override
    public void onMessage(Message message) {
        System.out.println("object is printed out");
        try {

            InitialContext context = new InitialContext();
            IoTDeviceReadingStoreBeanDTO dto = message.getBody(IoTDeviceReadingStoreBeanDTO.class);

            dbConnectionBean.addNewDevice(dto);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
}
