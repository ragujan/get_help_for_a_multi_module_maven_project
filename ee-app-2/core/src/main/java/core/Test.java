package core;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/iot_simulator_app?enabledTLSProtocols=TLSv1.2&useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "ragJN100Mania");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void getAllReadings() {
        Connection connection = getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select * from `iot_device_readings`");

            List<IoTDataHolder> deviceReadings = new ArrayList<>();
            while (resultSet.next()) {
                IoTDataHolder ioTDataHolder = new IoTDataHolder();
                int id = resultSet.getInt("id");
                String deviceId = resultSet.getString("device_id");
                String registeredAt = resultSet.getString("registered_at");
                String speed = resultSet.getString("speed");
                String trafficLightSignal = resultSet.getString("traffic_light_signal");
                String longitude = resultSet.getString("longitude");
                String latitude = resultSet.getString("latitude");
                String direction = resultSet.getString("direction");

                ioTDataHolder.setId(id);
                ioTDataHolder.setDeviceId(deviceId);
                ioTDataHolder.setRegisteredAt(registeredAt);
                ioTDataHolder.setSpeed(speed);
                ioTDataHolder.setTrafficLightSignal(trafficLightSignal);
                ioTDataHolder.setLongitude(longitude);
                ioTDataHolder.setLatitude(latitude);
                ioTDataHolder.setDirection(direction);
                deviceReadings.add(ioTDataHolder);
            }

            deviceReadings.forEach(e-> System.out.println(e.getId()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        getAllReadings();
    }
}
