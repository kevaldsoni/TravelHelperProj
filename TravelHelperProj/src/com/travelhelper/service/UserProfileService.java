package com.travelhelper.service;

import java.util.List;

import com.travelhelper.model.UserProfile;

public interface UserProfileService {

	public UserProfile findById(int id);
	public List<UserProfile> listProfile();
	public int createNewUserProfile(UserProfile profile);
	public boolean saveGoogleNotificationId(String id);
	
}
