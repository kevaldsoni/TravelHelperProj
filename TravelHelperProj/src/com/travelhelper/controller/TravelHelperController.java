package com.travelhelper.controller;


import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.travelhelper.model.UserProfile;
import com.travelhelper.service.UserProfileService;


@Controller
public class TravelHelperController {
	
	
	private static Logger log=Logger.getLogger(TravelHelperController.class.getName());
	
	@Autowired
	private UserProfileService userProfileService;
	
	
	/*public void setUserProfileService(UserProfileService userProfileService){
		this.userProfileService = userProfileService;
	}*/
	
	/*@RequestMapping("/welcome")
	public ModelAndView helloWorld(){
		System.out.println("In TravelHelperController :: method helloWorld");
		log.info("In TravelHelperController :: method helloWorld");
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello second page </h3></div><br><br>";
		return new ModelAndView("welcome", "message", message);
	
	}*/
	
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
	
	
}
