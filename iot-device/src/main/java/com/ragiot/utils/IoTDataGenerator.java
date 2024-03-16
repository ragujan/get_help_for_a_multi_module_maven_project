package com.ragiot.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.Coordinate;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class IoTDataGenerator {
    public static void generate() throws IOException {
//        String urlText = "https://perchance.org/us-geographic-location";
//        URL url = new URL(urlText);
//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.connect();

//        int responseCode = conn.getResponseCode();
//
//        System.out.println(responseCode);


    }
    public static Coordinate generateRandomCoordinate() {

        try {
            InputStream inputStream = IoTDataGenerator.class.getResourceAsStream("/coordinates.json");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(inputStream);
            JsonNode coordinatesNode = jsonNode.get("coordinates");
            List<Coordinate> coordinateList = new LinkedList<>();
            for (JsonNode coordinateNode : coordinatesNode) {
                String direction = coordinateNode.get("direction").asText();
                double latitude = coordinateNode.get("latitude").asDouble();
                double longitude = coordinateNode.get("longitude").asDouble();

                Coordinate coordinate = new Coordinate();
                coordinate.setDirection(direction);
                coordinate.setLatitude(latitude);
                coordinate.setLongitude(longitude);
                coordinateList.add(coordinate);
            }


            int randomIndex = new Random().nextInt(coordinateList.size());
            Coordinate randomCoordinate = coordinateList.get(randomIndex);

            return randomCoordinate;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    public static String randomNumberToString(){
        Random random = new Random();
        int randomNumber = random.nextInt();

        // Convert the random number to a string
        String randomString = Integer.toString(randomNumber);
        return randomString;
    }
    public static String randomStatus() {

        List<String> lightStatus = new LinkedList<>();
        lightStatus.add("Red");
        lightStatus.add("Green");
        lightStatus.add("Yellow");
        int val = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        return lightStatus.get(val);
    }
    public static int randomSpeed(){

        int val = ThreadLocalRandom.current().nextInt(5, 70 + 1);
        return val;
    }

    public static void main(String[] args) throws IOException {
        generate();
        System.out.println(randomStatus());
        System.out.println(randomSpeed());
    }
}
