package core;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class IoTDeviceReadingStoreBeanDTO implements   Serializable {
    private List<IoTDeviceReadingState> readings ;
    private String name;


    public void setName(String name) {
        this.name=name;
    }

    public String getName() {
        return this.name;
    }

    public void setReading(List<IoTDeviceReadingState> readings){
        this.readings = readings;
    }
    public List<IoTDeviceReadingState> getReadings(){
        return readings;
    }

}
