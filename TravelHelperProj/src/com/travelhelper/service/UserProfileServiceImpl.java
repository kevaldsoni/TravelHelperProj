package com.travelhelper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelhelper.dao.UserProfileDAO;
import com.travelhelper.model.UserProfile;

@Service
public class UserProfileServiceImpl implements UserProfileService{
	
	@Autowired
	private UserProfileDAO userProfileDao;

	
	/*public void setDao(UserProfileDAO dao) {
		this.dao = dao;
	}*/

	@Override
	@Transactional
	public UserProfile findById(int id) {
		// TODO Auto-generated method stub
		System.out.println("In UserProfileServiceImpl :: findById");
		return this.userProfileDao.findById(id);
	}

	@Override
	@Transactional
	public List<UserProfile> listProfile() {
		// TODO Auto-generated method stub
		System.out.println("In UserProfileServiceImpl :: listProfile");
		return this.userProfileDao.listProfile();
	}
	
	@Override
	@Transactional
	public int createNewUserProfile(UserProfile profile) {
		// TODO Auto-generated method stub
		System.out.println("In UserProfileServiceImpl :: createNewUserProfile");
		return this.userProfileDao.addNewUserDetailsToAccount(profile);
	}
	
	
}
