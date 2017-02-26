package com.travelhelper.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;
import com.travelhelper.model.AjaxResponseBody;
import com.travelhelper.model.FutureTravel;
import com.travelhelper.model.SearchCriteria;
import com.travelhelper.model.TravelModeSelected;
import com.travelhelper.model.User;
import com.travelhelper.model.Views;
import com.travelhelper.service.TravelService;
import com.travelhelper.service.UserProfileService;

@RestController
public class AjaxController {
	

	List<User> users;

	
	@Autowired
	private TravelService travelService;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/getSearchResult")
	public AjaxResponseBody getSearchResultViaAjax(@RequestBody SearchCriteria search) {
		System.out.println(search.getUsername());
		AjaxResponseBody result = new AjaxResponseBody();

		if (isValidSearchCriteria(search)) {
			List<User> users = findByUserNameOrEmail(search.getUsername(), search.getEmail());

			if (users.size() > 0) {
				result.setCode("200");
				result.setMsg("");
				result.setResult(users);
			} else {
				result.setCode("204");
				result.setMsg("No user!");
			}

		} else {
			result.setCode("400");
			result.setMsg("Search criteria is empty!");
		}

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}

	private boolean isValidSearchCriteria(SearchCriteria search) {

		boolean valid = true;

		if (search == null) {
			valid = false;
		}

		if ((StringUtils.isEmpty(search.getUsername())) && (StringUtils.isEmpty(search.getEmail()))) {
			valid = false;
		}

		return valid;
	}

	// Init some users for testing
	@PostConstruct
	private void iniDataForTesting() {
		users = new ArrayList<User>();
		System.out.println("Initializing users");
		User user1 = new User("a", "pass123", "ag@yahoo.com", "012-1234567", "address 123");
		User user2 = new User("yflow", "pass456", "yflow@yahoo.com", "016-7654321", "address 456");
		User user3 = new User("laplap", "pass789", "mang@yahoo.com", "012-111111", "address 789");
		users.add(user1);
		users.add(user2);
		users.add(user3);

	}

	// Simulate the search function
	private List<User> findByUserNameOrEmail(String username, String email) {

		List<User> result = new ArrayList<User>();

		for (User user : users) {

			if ((!StringUtils.isEmpty(username)) && (!StringUtils.isEmpty(email))) {

				if (username.equals(user.getUsername()) && email.equals(user.getEmail())) {
					result.add(user);
					continue;
				} else {
					continue;
				}

			}
			if (!StringUtils.isEmpty(username)) {
				if (username.equals(user.getUsername())) {
					result.add(user);
					continue;
				}
			}

			if (!StringUtils.isEmpty(email)) {
				if (email.equals(user.getEmail())) {
					result.add(user);
					continue;
				}
			}

		}

		return result;

	}
	
	
	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/saveTravelSelection")
	public AjaxResponseBody saveTravelOptionSelected(@RequestBody TravelModeSelected travelData) {
		System.out.println("In Ajax Controller ::"+travelData.getDrive()+" "+travelData.getModeName());
		AjaxResponseBody result = new AjaxResponseBody();
		int id = travelService.saveTravelModeSelected(travelData);
		if (id > 0) {
				System.out.println("Travel Data Saved");
				result.setCode("200");
				result.setMsg("");
				result.setResult(users);
			} else {
				result.setCode("204");
				result.setMsg("No user!");
			}

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}
	
	
	
	@JsonView(Views.Public.class)
	@RequestMapping (value = "/saveFutureTravelDetails")
	public AjaxResponseBody saveFutureTravelDetails(HttpServletRequest request) throws ParseException{
		System.out.println("In Ajax Controller ::saveFutureTravelDetails");
		AjaxResponseBody result = new AjaxResponseBody();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 
	    int userId = userProfileService.fetchUserIdfromUsername(name);
	    System.out.println("Logged in user id : "+userId);
	    if(userId > 0 ){
	    	FutureTravel ft  = new FutureTravel();
	    	ft.setUserId(userId);
	    	ft.setStartLatitude(request.getParameter("startLatitude"));
	    	ft.setStartLongitude(request.getParameter("startLongitude"));
	    	ft.setEndLatitude(request.getParameter("endLatitude"));
	    	ft.setEndLongitude(request.getParameter("endLongitude"));
	    	ft.setTravelDriveSelected(request.getParameter("travelDriveSelected"));
	    	String notifyBefore = (String)request.getParameter("preNotificationTime");
	    	int preNotifyTime = Integer.parseInt(notifyBefore);
	    	ft.setPreNotificationTime(preNotifyTime);
	    	// save travel_id in travelservice.java
	    	
	    	// This time needs to be calculated properly reachtime - timetakenfordrive - eta 
	    	String destReachTime = (String)request.getParameter("destReachTime");
	    	/*String data [] = destReachTime.split(" ");
	    	if(data != null && data.length > 0){
	    		
	    		String date[] = data[0].split("-");
	    		System.out.println(date[0]+ " "+date[1]+" "+date[2]);
	    		String time[] = data[1].split(":");
	    		System.out.println(time[0]+" "+time[1]);
	    		
	    	}*/
	    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    	Date destinationTime = df.parse(destReachTime);
	    	ft.setDestinationReachTime(destinationTime);
	    	
	    	int travelTimeinMin = Integer.parseInt(request.getParameter("expectedTravelTime"));
	    	ft.setTraveltimeExpected(travelTimeinMin);
	    	
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(destinationTime);
	    	int totalBackTime = preNotifyTime+travelTimeinMin;
	    	cal.add(Calendar.MINUTE, -totalBackTime);
	    	String notifyTime = df.format(cal.getTime());
	    	ft.setNotificationTime(cal.getTime());
	    	System.out.println("Time at which notification needs to be sent : "+notifyTime);
	    	
	    	int recordId = travelService.saveFutureScheduledRequest(ft);
				System.out.println("Future Travel Data Saved "+recordId);
				result.setCode("200");
				result.setMsg("");
				result.setResult(users);
			} else {
				result.setCode("204");
				result.setMsg("No user!");
			}
		return result;
	}
	
	 @Scheduled(initialDelay=5000 , fixedDelay=60000)
	 public void checkForNotification() {
		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss");
	        // something that should execute periodically
	    	Date now = new Date();
	    	System.out.println("in ajax controller Check now "+df.format(now));
	    	List<Integer> ids = userProfileService.fetchUserTobeNotified(now);
	    	List<String> cliendIdList = userProfileService.fetchClientIdForNotification(ids);
	    	for(String clientId : cliendIdList){
	    		travelService.pushFCMNotification(clientId);
	    	}
	    	//String clientDeviceID = "ewZeqppUqEI:APA91bHdpNf15Hp239u1jWLuUcYQb0a9QFd8Ok61FvdJZTk56Hw4d1LNO7r6TXJ2-gNZIrbJNzAGAZ7VYGWNZ-OYnnJAauKjd8ejYh-Epuw8PMk6UKmunvn2YYghrPDPV0jvUdjM57Eq";
	    	
	 }
	
}
