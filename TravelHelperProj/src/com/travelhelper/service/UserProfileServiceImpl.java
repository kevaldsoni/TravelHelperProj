package com.travelhelper.service;

import java.util.Date;
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

	@Override
	public boolean saveGoogleNotificationId(String id) {
		// TODO Auto-generated method stub
		userProfileDao.saveGoogleNotificationId(id);
		return false;
	}

	@Override
	public boolean updatelastUsedGcmId(int userId) {
		// TODO Auto-generated method stub
		userProfileDao.updatelastUsedGcmId(userId);
		return false;
	}

	@Override
	public int fetchUserIdfromUsername(String username) {
		// TODO Auto-generated method stub
		return userProfileDao.fetchUserIdfromUsername(username);
	}

	@Override
	public List<Integer> fetchUserTobeNotified(Date now) {
		// TODO Auto-generated method stub
		return userProfileDao.fetchUserTobeNotified(now);
	}

	@Override
	public List<String> fetchClientIdForNotification(List<Integer> ids) {
		// TODO Auto-generated method stub
		return userProfileDao.fetchClientIdForNotification(ids);
	}

	@Override
	public boolean checkUsernameAlreadyExists(String username) {
		// TODO Auto-generated method stub
		return userProfileDao.checkUsernameAlreadyExists(username);
	}
	
	
}
