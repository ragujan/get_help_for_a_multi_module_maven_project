package core.dto;


import core.model.IoTDeviceReadingState;

import java.io.Serializable;

public class IoTDeviceReadingStoreBeanDTO implements   Serializable {
    private IoTDeviceReadingState readings ;
    private String name;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getName() {
        return this.name;
    }

    public void setReading(IoTDeviceReadingState readings){
        this.readings = readings;
    }
    public IoTDeviceReadingState getReadings(){
        return readings;
    }

}
