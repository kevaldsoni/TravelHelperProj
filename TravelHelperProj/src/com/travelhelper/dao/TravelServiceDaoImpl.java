package com.travelhelper.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.travelhelper.model.FutureTravel;
import com.travelhelper.model.TravelDrive;
import com.travelhelper.model.TravelModeSelected;


import com.travelhelper.configuration.Constants;

@Repository
@EnableTransactionManagement
public class TravelServiceDaoImpl implements TravelServiceDao{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public int saveUserTravelSelection(TravelModeSelected travelPref) {
		

		System.out.println("Save Travel Preference Selection in Database : saveUserTravelSelection :"+travelPref.getUserDrive());
		travelPref.setUserId(2);
		System.out.println("User id :"+travelPref.getUserId());
		System.out.println("start x cord :"+travelPref.getSourceLatitude());
		System.out.println("start y cord :"+travelPref.getSourceLongitude());
		System.out.println("end x cord :"+travelPref.getDestLatitude());
		System.out.println("end x cord :"+travelPref.getDestLongitude());
		
		Date now = new Date();
		travelPref.setRequestTimeStamp(now);
		
			
		System.out.println("distance :"+travelPref.getDistance());
		System.out.println("Drive id :"+travelPref.getDrive());
		
		travelPref.setTravelMode(1);
		System.out.println("Mode id :"+travelPref.getTravelMode());
		
		travelPref.setTravelStatus("STARTED");
		System.out.println("travel status :"+travelPref.getTravelStatus());
		
		System.out.println("travel duration :"+travelPref.getDuration());
		
		int id=0;
		Transaction tx = null;
		Session session = this.sessionFactory.openSession();
		try{
			tx=session.beginTransaction();
			id = (Integer)session.save(travelPref);			
			if(id > 0){
				System.out.println("Travel Preference saved new record :: "+id);
			}else{
				System.out.println("Travel Preference Save Failed");
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return id;

	}
	

	public int fetchDriveIdFromName(String driveName){
		int driveId=0;
		Transaction tx = null;
		Session session = this.sessionFactory.openSession();
		try{
			tx=session.beginTransaction();
			Criteria cr = session.createCriteria(TravelDrive.class);
			cr.add(Restrictions.eq("driveName", driveName));
			List results = cr.list();
			
			if(results!=null && results.size()>0){
				
				for (Iterator iterator = results.iterator(); iterator.hasNext();){
					TravelDrive pobj = (TravelDrive) iterator.next(); 
					driveId= pobj.getDriveId();
				}
			}else{
				System.out.println("fetchDriveIdFromName :: No result found");
			}
			tx.commit();
			
		}catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return driveId;
		
	}

	@Override
	public int saveFutureScheduledRequest(FutureTravel fTravel) {
		// TODO Auto-generated method stub
		int recordId = 0;
		Transaction tx = null;
		Session session = this.sessionFactory.openSession();
		try{
			tx=session.beginTransaction();
			recordId = (Integer)session.save(fTravel);			
			if(recordId > 0){
				System.out.println("Future Travel saved new record :: "+recordId);
			}else{
				System.out.println("Travel Preference Save Failed");
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return recordId;
	}

	@Override
	public String retrieveUberAuthenticationToken(String code) {
		String accessToken =null;
		System.out.println("!! Get Access token input param:: "+code);
		
			URL tokenEndpointUrl;
			try {
				tokenEndpointUrl = new URL(getUberTokenEndpointGraphUrl(code));
				System.out.println(tokenEndpointUrl);
			
			HttpURLConnection urlconn = null;
			urlconn = (HttpURLConnection) tokenEndpointUrl.openConnection();
			urlconn.setInstanceFollowRedirects(true);
			urlconn.setRequestMethod("POST");
			urlconn.setDoOutput(true);
			urlconn.connect();
			int respCode = urlconn.getResponseCode();
			String errMsg = urlconn.getResponseMessage();
			System.out.println(respCode+" "+errMsg);
			
			  String lStream = "";
			  BufferedReader inp = new BufferedReader(new InputStreamReader(
			     urlconn.getInputStream()));
			   while ((lStream = inp.readLine()) != null) {
				   accessToken = accessToken + lStream;
			   }
			   inp.close();
			   urlconn.disconnect();
			 	{
			 		System.out.println("Response :: "+accessToken);
			 	}
			   
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Invalid code received " + e);
			}
		return accessToken;

	}
	
	public static String getUberTokenEndpointGraphUrl(String code) {
		String tokenEndPoint = "";
		try {
			tokenEndPoint = "https://login.uber.com/oauth/v2/token?"
					+"client_id=" + Constants.UBER_CLIENT_ID +
					"&client_secret=" + Constants.UBER_CLIENT_SECRET +
					"&grant_type="+ Constants.GRANT_TYPE+
					"&redirect_uri="+URLEncoder.encode("http://localhost:8080/TravelHelper/uberoauth", "UTF-8")+
					"&code=" +code+
					"&scope=profile";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenEndPoint;
	}

}
