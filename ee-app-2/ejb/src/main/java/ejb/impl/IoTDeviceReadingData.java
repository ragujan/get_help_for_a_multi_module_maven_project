package ejb.impl;


import core.IoTDataHolder;
import jakarta.ejb.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Local
@Stateless
//@DependsOn("DbConnectionBean")
public class IoTDeviceReadingData {
    @EJB
    DbConnectionBean dbConnectionBean;

    public List<IoTDataHolder> getAllReadings() {
        Connection connection = dbConnectionBean.getConnection();
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

            deviceReadings.forEach(e -> System.out.println(e.getRegisteredAt()));
            return deviceReadings;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
