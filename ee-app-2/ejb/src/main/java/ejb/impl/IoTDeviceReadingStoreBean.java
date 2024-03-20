package ejb.impl;

import core.model.IoTDeviceReadingState;
import core.dto.IoTDeviceReadingStoreBeanDTO;
import ejb.remote.IoTDeviceReadingStore;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateful;

import java.io.Serializable;

@Stateful
public class IoTDeviceReadingStoreBean implements IoTDeviceReadingStore , Serializable {
    private IoTDeviceReadingState readings ;
    private String name;

    @PostConstruct
    public void configureBean(){

    }

    @Override
    public IoTDeviceReadingStoreBeanDTO getDTO() {
        IoTDeviceReadingStoreBeanDTO dto = new IoTDeviceReadingStoreBeanDTO();
        dto.setName(this.name);
        dto.setReading(this.getReadings());
        return dto;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setReading(IoTDeviceReadingState reading){
        this.readings=reading;
    }
    public IoTDeviceReadingState getReadings(){
        return readings;
    }

}
