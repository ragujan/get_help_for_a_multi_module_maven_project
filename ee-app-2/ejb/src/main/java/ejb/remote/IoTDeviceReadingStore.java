package ejb.remote;

import core.model.IoTDeviceReadingState;
import core.dto.IoTDeviceReadingStoreBeanDTO;
import jakarta.ejb.Remote;

import java.io.Serializable;

@Remote
public interface IoTDeviceReadingStore extends Serializable {

    void configureBean();

    IoTDeviceReadingStoreBeanDTO getDTO();

    void setName(String name);
    String getName();

    void setReading(IoTDeviceReadingState reading);

    IoTDeviceReadingState getReadings();
}

