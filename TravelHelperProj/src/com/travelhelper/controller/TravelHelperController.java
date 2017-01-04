package com.travelhelper.controller;


import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
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
}
