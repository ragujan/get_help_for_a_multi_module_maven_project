package ejb.impl;

import ejb.remote.IoTDeviceManager;
import jakarta.ejb.Stateless;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class IoTDeviceManagerBean implements IoTDeviceManager {

    public List<String> getDeviceIds() {
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

            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select * from iot_device");
            String id;
            List<String> iotIds = new LinkedList<>();
            while (resultSet.next()) {
                id = Integer.toString(resultSet.getInt("id"));
                iotIds.add(id);
            }
            resultSet.close();
            statement.close();
            connection.close();
            iotIds.forEach(e -> System.out.println(e));
            return iotIds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
