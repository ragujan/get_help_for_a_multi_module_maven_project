package ejb.message;

import core.IoTDeviceReadingStoreBeanDTO;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Local;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import java.sql.*;

@Startup
@Local
@Singleton
public class DbConnectionBean {
    private Connection connection;

    @PostConstruct
    public void initialize() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/iot_simulator_app?enabledTLSProtocols=TLSv1.2&useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "ragJN100Mania");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to obtain database connection", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void addNewDevice(IoTDeviceReadingStoreBeanDTO dto) {
        Connection connection = this.getConnection();
        String deviceId = dto.getId();
        String registeredAt = dto.getReadings().getCapturedTime();
        String speed = Integer.toString(dto.getReadings().getCapturedVehicleSpeed());
        String trafficLightStatus = dto.getReadings().getCapturedTrafficLightStatus();
        String longitute = Double.toString(dto.getReadings().getCapturedGPSCoordinates().getLongitude());
        String latitute = Double.toString(dto.getReadings().getCapturedGPSCoordinates().getLatitude());
        String direction = dto.getReadings().getCapturedGPSCoordinates().getDirection();
        try {

            Statement statement;
            statement = connection.createStatement();
            String insertQuery = "INSERT INTO `iot_device_readings` ( `device_id`, `registered_at`, `speed`, `traffic_light_signal`, `longititute`, `latitute`, `direction`)\n" +
                    "VALUES ( '" + deviceId + "', '" + registeredAt + "', '" + speed + "', '" + trafficLightStatus + "', '" + longitute + "', '" + latitute + "', '" + direction + "');";
            statement.executeUpdate(insertQuery);
            statement.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }

    }

    @PreDestroy
    public void closeConnection() {
        // Close resources on bean destruction
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Log or handle the exception
            }
        }
    }

}
