package com.travelhelper.service;

import java.util.Date;
import java.util.List;

import com.travelhelper.model.UserProfile;

public interface UserProfileService {

	public UserProfile findById(int id);
	
	public List<UserProfile> listProfile();
	
	public int createNewUserProfile(UserProfile profile);
	
	public boolean saveGoogleNotificationId(String id,int userId);
	
	public boolean updatelastUsedGcmId(int userId);
	
	public int fetchUserIdfromUsername(String username);
	
	public List<Integer> fetchUserTobeNotified(Date now);
	
	public List<String> fetchClientIdForNotification(List<Integer> ids);
	
	public boolean checkUsernameAlreadyExists (String username);
	
	
}
