package ejb.remote;

import core.IoTDeviceReadingState;
import core.IoTDeviceReadingStoreBeanDTO;
import jakarta.ejb.Remote;

import java.io.Serializable;
import java.util.List;

@Remote
public interface IoTDeviceReadingStore extends Serializable {

    void configureBean();

    IoTDeviceReadingStoreBeanDTO getDTO();

    void setName(String name);
    String getName();

    void setReading(IoTDeviceReadingState reading);

    List<IoTDeviceReadingState> getReadings();
}

