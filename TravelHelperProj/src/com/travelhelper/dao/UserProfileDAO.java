package com.travelhelper.dao;

import java.util.List;

import com.travelhelper.model.UserProfile;

public interface UserProfileDAO {
	
	public UserProfile findById (int id);
	public List<UserProfile> listProfile();
}
