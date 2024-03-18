package ejb.impl;


import core.IoTDataHolder;
import jakarta.ejb.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    public double getTrafficDensity() {
        Connection connection = dbConnectionBean.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet northVehicles = statement.executeQuery(
                    "select * from `iot_device_readings` where `direction`='North'");
            double northDistance = 0.4;
            double vehicleCountNorth = 0;

            while (northVehicles.next()) {
                vehicleCountNorth++;
            }
            double northAvgVehicleDensity = (vehicleCountNorth / northDistance);
//            System.out.println("avg vehicle north density " + northAvgVehicleDensity);

            ResultSet eastVehicles = statement.executeQuery(
                    "select * from `iot_device_readings` where `direction`='East'");
            double eastDistance = 0.4;
            double vehicleCountEast = 0;

            while (eastVehicles.next()) {
                vehicleCountEast++;
            }
            double eastAvgVehicleDensity = (vehicleCountEast / eastDistance);
//            System.out.println("avg vehicle east density " + eastAvgVehicleDensity);

            ResultSet southVehicles = statement.executeQuery(
                    "select * from `iot_device_readings` where `direction`='South'");
            double southDistance = 1.2;
            double vehicleCountSouth = 0;

            while (southVehicles.next()) {
                vehicleCountSouth++;
            }
            double southAvgVehicleDensity = (vehicleCountSouth / southDistance);
//            System.out.println("avg vehicle south density " + southAvgVehicleDensity);

            ResultSet southWestVehicles = statement.executeQuery(
                    "select * from `iot_device_readings` where `direction`='South West'");
            double southWestDistance = 0.3;
            double vehicleCountSouthWest = 0;

            while (southWestVehicles.next()) {
                vehicleCountSouthWest++;
            }
            double southWestAvgVehicleDensity = (vehicleCountSouthWest / southWestDistance);
//            System.out.println("avg vehicle south west density " + southWestAvgVehicleDensity);

            return ((northAvgVehicleDensity + eastAvgVehicleDensity + southAvgVehicleDensity + southWestAvgVehicleDensity)/4);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0.00;
    }

    public double getAverageVehicleSpeed() {
        Connection connection = dbConnectionBean.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select * from `iot_device_readings`");

            double averageSpeed = 0;
            int count = 1;
            int totalSpeed = 0;
            while (resultSet.next()) {
                String speed = resultSet.getString("speed");
                totalSpeed = Integer.parseInt(speed) + totalSpeed;
                averageSpeed = (Double.parseDouble(Integer.toString(totalSpeed))) / count;
                count++;
            }
            return averageSpeed;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }


    public double getTrafficFlowAnalysis() {

//        The traffic flow analysis originally comes from
//        fluid mechanism theory.
//        When average vehicle speed (v) (km/h)on a road and
//        the density of vehicle which is
//        so called Traffic Density (k)(veh/km),
//        the Traffic Volume (q) (veh/h) is obtained by

//        q=k×v
        return getTrafficDensity()*getAverageVehicleSpeed();
    }

    public  String getCalcuatedTrafficFlowByTime(int hours){
        LocalDate today = LocalDate.now();
//        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime startOfDay = LocalDateTime.now();
//        LocalDateTime endOfDay = today.atTime(23, 59, 59);
        LocalDateTime endOfDay = LocalDateTime.now().minusHours(hours);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startTimestamp = startOfDay.format(formatter);
        String endTimestamp = endOfDay.format(formatter);

//        System.out.println("start "+startTimestamp);
//        System.out.println("end "+endTimestamp);

        Connection connection = dbConnectionBean.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select * from `iot_device_readings` WHERE" +
                            " `registered_at` BETWEEN '"+endTimestamp+"' AND '"+startTimestamp+"'");

            int totalVehicle = 0;
            while (resultSet.next()) {
                String speed = resultSet.getString("speed");
//                System.out.println(speed);
                totalVehicle++;
            }
            float flTotalVehicleCount = totalVehicle;
            float flHours = hours;

            Float trafficFlowValue =  (flTotalVehicleCount/flHours);
//            System.out.println("traffic flow is "+trafficFlowValue);
            return Float.toString(trafficFlowValue);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return "";
    }
    public double getAverageTravelSpeed(){
        return getTrafficDensity()*getAverageVehicleSpeed();
    }

}
