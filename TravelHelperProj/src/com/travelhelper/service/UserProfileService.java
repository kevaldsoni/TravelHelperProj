package com.travelhelper.service;

import java.util.List;

import com.travelhelper.model.UserProfile;

public interface UserProfileService {

	public UserProfile findById(int id);
	public List<UserProfile> listProfile();

	
}
