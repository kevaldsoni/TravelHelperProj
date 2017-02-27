package com.travelhelper.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.travelhelper.configuration.ResourceNotFoundException;
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
		model.addAttribute("errormessage","Authentication Failed");
		return "login";
	}
	
	
	@RequestMapping(value="/logout")
	public String logoutPage (ModelMap model,HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "login";
	}
	
	
	@RequestMapping("/signupform")
	public String processSignupForm(ModelMap model,UserProfile profile) {
		System.out.println("First Name : "+profile.getFirstName());
		boolean userExists = userProfileService.checkUsernameAlreadyExists(profile.getUsername());
		if(!userExists){
			int id = userProfileService.createNewUserProfile(profile);
			if(id > 0){
				model.addAttribute("successMessage", "Account Created Successfully. Login to explore travel helper.");
			}
		}else{
				model.addAttribute("errormessage", "Username already used. Kindly sign up with another one.");
		}
		
		return "login";
	}
	

	
}
