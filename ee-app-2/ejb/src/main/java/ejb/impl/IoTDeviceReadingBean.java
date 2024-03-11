package ejb.remote;


import core.Coordinate;
import ejb.remote.IoTDeviceReading;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;

@Stateless
public class IoTDeviceReadingBean implements IoTDeviceReading {
    int capturedVehicleSpeed = 0;
    String capturedTrafficLightStatus = null;
    Coordinate capturedGPSCoordinates = null;


    @Override
    public void captureVehicleSpeed(int speed) {
        this.capturedVehicleSpeed = speed;
    }

    @Override
    public void captureTrafficLightStatus(String status) {
        this.capturedTrafficLightStatus = status;
    }

    @Override
    public void captureGPSCoordinates(Coordinate value) {
        this.capturedGPSCoordinates = value;
    }

    @Override
    public int getCapturedVehicleSpeed() {
        return capturedVehicleSpeed;
    }

    @Override
    public String getCapturedTrafficLightStatus() {
        return capturedTrafficLightStatus;
    }

    @Override
    public Coordinate getCapturedGPSCoordinates() {
        return capturedGPSCoordinates;
    }
}
