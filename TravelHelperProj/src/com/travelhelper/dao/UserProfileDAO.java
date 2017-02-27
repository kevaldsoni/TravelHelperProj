package com.travelhelper.dao;

import java.util.Date;
import java.util.List;

import com.travelhelper.model.UserProfile;

public interface UserProfileDAO {
	
	public UserProfile findById (int id);
	
	public List<UserProfile> listProfile();
	
	public int addNewUserDetailsToAccount(UserProfile profile);
	
	public boolean saveGoogleNotificationId(String id);
	
	public boolean updatelastUsedGcmId(int userId);
	
	public int fetchUserIdfromUsername(String username);
	
	public List<Integer> fetchUserTobeNotified(Date now);
	
	public List<String> fetchClientIdForNotification(List<Integer> ids);
	
	public boolean checkUsernameAlreadyExists (String username);
}
