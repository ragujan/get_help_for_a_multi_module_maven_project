package com.ragiot.utils;

import java.io.IOException;
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
