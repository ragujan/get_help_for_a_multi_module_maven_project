package com.ragiot.view;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.shaded.com.fasterxml.jackson.jr.ob.impl.JSONReader;
import core.Coordinate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TestJson {
    public static Coordinate generateRandomCoordinate() {

        try {
            InputStream inputStream = TestJson.class.getResourceAsStream("/coordinates.json");
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


    public static void main(String[] args) {

        try {
            InputStream inputStream = TestJson.class.getResourceAsStream("/coordinates.json");
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
            Coordinate randomValue = coordinateList.get(randomIndex);

            System.out.println("random co ordinate is "+randomValue.getLatitude());
            System.out.println("random co ordinate is "+randomValue.getLongitude());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
