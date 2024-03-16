package core;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DeviceManagerUtil {

    public Connection getConnection() {
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

    public void addNewDevice() {
        Connection connection = getConnection();

        try {

            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select * from user");
            String name;
            while (resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println("Code : " + name
                );
            }

            statement.executeUpdate("INSERT INTO iot_device VALUES (DEFAULT)");
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("hey");
        } catch (Exception exception) {
            System.out.println(exception);
        }

    }

    public List<String> getIoTDevices() {
        List<String> empty = new LinkedList<>();
        Connection connection = this.getConnection();
        try {
            if (connection.isClosed()) {
                System.out.println("The connection is not there");
                return empty;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
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

//            iotIds.forEach(e -> System.out.println(e));
            return iotIds;
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return empty;
    }
    public void enterIoTDeviceReading(){
        Connection connection = getConnection();

        try {

            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select * from user");
            String name;
            while (resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println("Code : " + name
                );
            }

            statement.executeUpdate("INSERT INTO iot_device VALUES (DEFAULT)");
            resultSet.close();
            statement.close();
//            connection.close();
            System.out.println("hey");
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }


}
