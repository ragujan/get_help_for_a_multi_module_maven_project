package core;

public interface IoTDeviceReading {
    public void captureVehicleSpeed(int speed);
    public void captureTrafficLightStatus(String status);
    public void captureGPSCoordinates(Coordinate value);

    public int getCapturedVehicleSpeed();
    public String getCapturedTrafficLightStatus();
    public Coordinate getCapturedGPSCoordinates();

}
