package com.travelhelper.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelhelper.dao.TravelServiceDao;
import com.travelhelper.model.FutureTravel;
import com.travelhelper.model.TravelModeSelected;

@Service
public class TravelServiceImpl implements TravelService{
	
	@Autowired
	private TravelServiceDao travelDao;
	
	public final static String AUTH_KEY_FCM = "AIzaSyAAZwbKFn9it2GY9bZWJwwvzYzdVKZiEno";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	@Override
	@Transactional
	public int saveTravelModeSelected(TravelModeSelected travelData) {
		int travelDriveId = travelDao.fetchDriveIdFromName(travelData.getUserDrive());
		travelData.setDrive(travelDriveId);
		return travelDao.saveUserTravelSelection(travelData);
	}

	@Override
	public void pushFCMNotification(String clientDeviceID) {
        String authKey = AUTH_KEY_FCM; // You FCM AUTH key
        String FMCurl = API_URL_FCM;
        try{
	        URL url = new URL(FMCurl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        conn.setUseCaches(false);
	        conn.setDoInput(true);
	        conn.setDoOutput(true);

	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "key=" + authKey);
	        conn.setRequestProperty("Content-Type", "application/json");

	        JSONObject data = new JSONObject();
	        data.put("to", clientDeviceID.trim());
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
	        
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
       
	}

	@Override
	public int saveFutureScheduledRequest(FutureTravel fTravel) {
		fTravel.setRequestSavetime(new Date());
		System.out.println("Travel Name : "+fTravel.getTravelDriveSelected());
		int driveID = travelDao.fetchDriveIdFromName(fTravel.getTravelDriveSelected());
		System.out.println("Drive ID in saveFutureScheduledRequest :"+driveID);
		fTravel.setTravelDriveId(driveID);
		return travelDao.saveFutureScheduledRequest(fTravel);
	}

	@Override
	public String getUberAuthentiationToken(String code) {
		return travelDao.retrieveUberAuthenticationToken(code);
	}
}
