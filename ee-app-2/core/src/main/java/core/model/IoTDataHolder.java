package core.model;

import java.io.Serializable;

public class IoTDataHolder implements Serializable {
    public int id;
    private String deviceId;
    private String registeredAt;
    private String speed;
    private String trafficLightSignal;
    private String longitude;
    private String latitude;
    private String direction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(String registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getTrafficLightSignal() {
        return trafficLightSignal;
    }

    public void setTrafficLightSignal(String trafficLightSignal) {
        this.trafficLightSignal = trafficLightSignal;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
