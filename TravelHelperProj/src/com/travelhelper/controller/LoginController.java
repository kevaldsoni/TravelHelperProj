package com.travelhelper.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.travelhelper.model.UserProfile;
import com.travelhelper.service.UserProfileService;

@Controller
public class LoginController {
	
	private static Logger log=Logger.getLogger(TravelHelperController.class.getName());
	
	@Autowired
	private UserProfileService userProfileService;
	
	@RequestMapping("/validateUserLogin")
	public ModelAndView checkLogin(){
		System.out.println("In TravelHelperController :: method checkLogin");
		log.info("In TravelHelperController :: method checkLogin");
		List<UserProfile> profile = userProfileService.listProfile();
		System.out.println("Size : "+profile.size());
		return new ModelAndView("logout", "message", "in home");
	}
	
	
	@RequestMapping("/login")
	public String login(ModelMap model) {
		System.out.println("In /login method");
		return "login";
	}
	
	@RequestMapping("/loginfailed")
	public String loginerror(ModelMap model) {
		System.out.println("In /loginfailed method");
		model.addAttribute("errormessage","true");
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(ModelMap model) {
		return "login";
	}
	
}
