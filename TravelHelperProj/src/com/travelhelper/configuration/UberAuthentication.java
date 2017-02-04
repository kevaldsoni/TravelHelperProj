package com.travelhelper.configuration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class UberAuthentication {
	
	public static final String UBER_APP_CLIENT_ID = "KqtkzZ7YHw8n6PJ96CA0wMJNbdeEO-mm";
	public static final String UBER_APP_SECRET = "5b4805ecf5e73a9b82ef668e7c9fc72f";
	//public static final String REDIRECT_URI = "http://localhost:8080/barter/facebookweb/home.jsp";
	public static final String REDIRECT_URI = "http://localhost:8080/TravelHelper/";
	static String accessToken = "";

	
	public String getUberOAuthUrl() {
		System.out.println("inside getUberOAuthUrl..");
		String uberLoginUrl = "";
		try {
			uberLoginUrl = "https://login.uber.com/oauth/v2/authorize?" + "client_id="
					+ UberAuthentication.UBER_APP_CLIENT_ID + "&response_type=code";
			System.out.println("Login URL fron uberLoginUrl ::"+uberLoginUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uberLoginUrl;
	}
}
