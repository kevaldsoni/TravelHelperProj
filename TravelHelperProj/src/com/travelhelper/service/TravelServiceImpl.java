package com.travelhelper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelhelper.dao.TravelServiceDao;
import com.travelhelper.model.TravelModeSelected;

@Service
public class TravelServiceImpl implements TravelService{
	
	@Autowired
	private TravelServiceDao travelDao;

	@Override
	@Transactional
	public int saveTravelModeSelected(TravelModeSelected travelData) {
		int travelDriveId = travelDao.fetchDriveIdFromName(travelData.getUserDrive());
		travelData.setDrive(travelDriveId);
		return travelDao.saveUserTravelSelection(travelData);
	}

}
