package com.travelhelper.controller;


import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.travelhelper.model.AjaxResponseBody;
import com.travelhelper.model.FutureTravel;
import com.travelhelper.model.TravelModeSelected;
import com.travelhelper.model.UserProfile;
import com.travelhelper.service.TravelService;
import com.travelhelper.service.UserProfileService;


@Controller
public class TravelHelperController {
	
	
	private static Logger log=Logger.getLogger(TravelHelperController.class.getName());
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private TravelService travelService;
	
	
	@RequestMapping(value="/welcome")
	public String helloWorld(ModelMap model,Principal principal){
		System.out.println("In TravelHelperController :: method helloWorld");
		String name = principal.getName();
		System.out.println(name);
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello second page </h3></div><br><br>";
		model.addAttribute("username", name);
		model.addAttribute("message",message);
		return "welcome";
	
	}
	
	@RequestMapping(value="/travelsearch")
	public String travelSearch(ModelMap model,Principal principal){
		System.out.println("In TravelHelperController :: method travelSearch");
		return "travelsearch";
	
	}
	
	@RequestMapping(value="/scheduletravel")
	public String scheduleTravel(ModelMap model,Principal principal){
		System.out.println("In TravelHelperController :: method scheduleTravel");
		
		return "scheduletravel";
	
	}
	
	@RequestMapping(value="/saveGcmIdForUser")
	public String saveGcmId(ModelMap model,HttpServletRequest request){
		System.out.println("In TravelHelperController :: method saveGcmId");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 
	    System.out.println("logged in username : "+name);
	    int userId = userProfileService.fetchUserIdfromUsername(name);
	    System.out.println("User id of logged in user :"+userId);
	    if(userId > 0 ){
	    	String gcmid = request.getParameter("id");
			userProfileService.updatelastUsedGcmId(userId);
			userProfileService.saveGoogleNotificationId(gcmid);
			model.addAttribute("message","Notification Enabled");
	    }
		
		return "scheduletravel";
	
	}
	
	@RequestMapping(value="/saveFutureTravel")
	public String saveFutureTravel(ModelMap model,HttpServletRequest request){
		System.out.println("In TravelHelperController :: method saveFutureTravel");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 
	    System.out.println("logged in username : "+name);
	    int userId = userProfileService.fetchUserIdfromUsername(name);
	    System.out.println("User id of logged in user :"+userId);
	    if(userId > 0 ){
	    	String gcmid = request.getParameter("id");
			userProfileService.updatelastUsedGcmId(userId);
			userProfileService.saveGoogleNotificationId(gcmid);
			model.addAttribute("message","Notification Enabled");
	    }
		
		return "scheduletravel";
	
	}
	
	@RequestMapping(value="/uberoauth")
	public String uberAuthenticationRedirect(ModelMap model,HttpServletRequest request){
	
	    	String code = request.getParameter("code");
			System.out.println("authorization code ::"+code);
			String token = request.getParameter("token");
			if(token== null || token.length() <=0){
				travelService.getUberAuthentiationToken(code);
			}
		return "welcome";
	
	}
	
	
	@RequestMapping(value="/dashboard")
	public String dashboardRedirect(ModelMap model,Principal principal){
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 
	    System.out.println("logged in username : "+name);
	    int userId = userProfileService.fetchUserIdfromUsername(name);
	    System.out.println("User id of logged in user :"+userId);
	    if(userId > 0 ){
	    	
	    	System.out.println("Fetching dashboard details");
	    }
		return "dashboard";
	
	}
	
	
	
	@RequestMapping(value="/travelhistory")
	public ModelAndView dashboardTravelHistory(ModelMap model,Principal principal){
		Map<String, Object> displayData = new HashMap<String, Object>();
		List<TravelModeSelected> list = new ArrayList<TravelModeSelected>();
		Map<String,Long> summaryMap = new HashMap<String,Long>();
		Map<String, Long> finalsortedsummaryMap = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 
	    System.out.println("logged in username : "+name);
	    int userId = userProfileService.fetchUserIdfromUsername(name);
	    System.out.println("User id of logged in user :"+userId);
	    if(userId > 0 ){
	    	System.out.println("Fetching dashboard travelhistory");
	    	list = travelService.getPastTravelHistory(userId);
	    	summaryMap = travelService.fetchTravelHistorySummaryBasedonDrive(userId);
	    	finalsortedsummaryMap = sortByValue(summaryMap);
	    }
		travelService.generateTravelHistroyPdf(list,userId,name);
		displayData.put("scheduleData", list);
		displayData.put("scheduleDataDriveSummary", finalsortedsummaryMap);
		return new ModelAndView("showTravelHistory",displayData);
	}
	
	@RequestMapping(value="/schedulehistory")
	public ModelAndView dashboardScheduleHistory(ModelMap model,Principal principal){
		Map<String, Object> displayData = new HashMap<String, Object>();
		List<FutureTravel> list = new ArrayList<FutureTravel>();
		Map<String,Long> summaryMap = new HashMap<String,Long>();
		Map<String, Long> finalsortedsummaryMap = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 
	    System.out.println("logged in username : "+name);
	    int userId = userProfileService.fetchUserIdfromUsername(name);
	    System.out.println("User id of logged in user :"+userId);
	    if(userId > 0 ){
	    	System.out.println("Fetching dashboard ScheduleHistory");
	    	list = travelService.getPastFutureScheduleHistory(userId);
	    	summaryMap = travelService.fetchScheduledTravelSummaryBasedonDrive(userId);
	    	finalsortedsummaryMap = sortByValue(summaryMap);
	    }
	    travelService.generateScheduleHistroyPdf(list, userId, name);
	    displayData.put("scheduleData", list);
	    displayData.put("scheduleDataDriveSummary", finalsortedsummaryMap);
		return new ModelAndView("showScheduleHistory",displayData);
	
	}
	
	@RequestMapping(value = "/handleTravelHistoryDownload")
	public String travelHistoryDownload(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 
	    System.out.println("logged in username : "+name);
	    int userId = userProfileService.fetchUserIdfromUsername(name);
	    if(userId > 0 ){
	    	travelService.fetchTravelHistoryDownload(request,response,userId);
	    }
		
		return "showTravelHistory";
	}
	
	@RequestMapping(value = "/handleScheduleHistoryDownload")
	public String scheduleHistoryDownload(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 
	    System.out.println("logged in username : "+name);
	    int userId = userProfileService.fetchUserIdfromUsername(name);
	    if(userId > 0 ){
	    	travelService.fetchScheduleHistoryDownload(request,response,userId);
	    }
		
		return "showScheduleHistory";
	}
	
	
	@RequestMapping(value="/filterschedulehistory")
	public ModelAndView filterschedulehistory(ModelMap model,Principal principal,HttpServletRequest request){
		System.out.println("In TravelHelperController :: method filterschedulehistory");
		Map<String, Object> displayData = new HashMap<String, Object>();
		List<FutureTravel> list = new ArrayList<FutureTravel>();
		Map<String,Long> summaryMap = new HashMap<String,Long>();
		Map<String, Long> finalsortedsummaryMap = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 
	    System.out.println("logged in username : "+name);
	    int userId = userProfileService.fetchUserIdfromUsername(name);
		String daterange = request.getParameter("daterange");
		String [] filterdates = daterange.split("-");
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
	    Date startDate;
	    Date endDate;
	    try {
	    	System.out.println(filterdates[0].trim()+" "+filterdates[1].trim());
	        startDate = df.parse(filterdates[0].trim());
	        endDate = df.parse(filterdates[1].trim());
	        list = travelService.getDateRangePastFutureScheduleHistory(userId,startDate,endDate);
	    	summaryMap = travelService.fetchDateRangeScheduledTravelSummaryBasedonDrive(userId, startDate, endDate);
	    	finalsortedsummaryMap = sortByValue(summaryMap);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		travelService.generateScheduleHistroyPdf(list, userId, name);
	    displayData.put("scheduleData", list);
	    displayData.put("scheduleDataDriveSummary", finalsortedsummaryMap);
		return new ModelAndView("showScheduleHistory",displayData);
	}
	
	@RequestMapping(value="/filtertravelhistory")
	public ModelAndView filtertravelhistory(ModelMap model,Principal principal,HttpServletRequest request){
		System.out.println("In TravelHelperController :: method filtertravelhistory");
		Map<String, Object> displayData = new HashMap<String, Object>();
		List<TravelModeSelected> list = new ArrayList<TravelModeSelected>();
		Map<String,Long> summaryMap = new HashMap<String,Long>();
		Map<String, Long> finalsortedsummaryMap = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); 
	    System.out.println("logged in username : "+name);
	    int userId = userProfileService.fetchUserIdfromUsername(name);
		String daterange = request.getParameter("daterange");
		String [] filterdates = daterange.split("-");
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
	    Date startDate;
	    Date endDate;
	    try {
	    	System.out.println(filterdates[0].trim()+" "+filterdates[1].trim());
	        startDate = df.parse(filterdates[0].trim());
	        endDate = df.parse(filterdates[1].trim());
	        list = travelService.getDateRangePastTravelHistory(userId, startDate, endDate);
	    	summaryMap = travelService.fetchDateRangeTravelHistorySummaryBasedonDrive(userId, startDate, endDate);
	    	finalsortedsummaryMap = sortByValue(summaryMap);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    travelService.generateTravelHistroyPdf(list,userId,name);
	    displayData.put("scheduleData", list);
	    displayData.put("scheduleDataDriveSummary", finalsortedsummaryMap);
	    return new ModelAndView("showTravelHistory",displayData);
	}
	
	
	private static Map<String, Long> sortByValue(Map<String, Long> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Long>> list = new LinkedList<Map.Entry<String, Long>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
            public int compare(Map.Entry<String, Long> o1,
                               Map.Entry<String, Long> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Long> sortedMap = new LinkedHashMap<String, Long>();
        for (Map.Entry<String, Long> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
		
}
