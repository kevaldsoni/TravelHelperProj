package com.travelhelper.configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FCMNotification {

    // Method to send Notifications from server to client end.
    public final static String AUTH_KEY_FCM = "AIzaSyAAZwbKFn9it2GY9bZWJwwvzYzdVKZiEno";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss");

    public static void pushFCMNotification(String DeviceIdKey) throws Exception {

        String authKey = AUTH_KEY_FCM; // You FCM AUTH key
        String FMCurl = API_URL_FCM;

        URL url = new URL(FMCurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + authKey);
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject data = new JSONObject();
        data.put("to", DeviceIdKey.trim());
        JSONObject info = new JSONObject();
        info.put("title", "FCM Notificatoin Title"); // Notification title
        info.put("body", "Hello First Test notification"); // Notification body
        data.put("data", info);

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data.toString());
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

    }
    
    @Scheduled(fixedRate=5000)
    public void doSomething() {
        // something that should execute periodically
    	Date now = new Date();
    	System.out.println("Check now "+df.format(now));
    }

    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {
        FCMNotification obj = new FCMNotification();
        obj.pushFCMNotification(
                "ewZeqppUqEI:APA91bHdpNf15Hp239u1jWLuUcYQb0a9QFd8Ok61FvdJZTk56Hw4d1LNO7r6TXJ2-gNZIrbJNzAGAZ7VYGWNZ-OYnnJAauKjd8ejYh-Epuw8PMk6UKmunvn2YYghrPDPV0jvUdjM57Eq");
    }
}
