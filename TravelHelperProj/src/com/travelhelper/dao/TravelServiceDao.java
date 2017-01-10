package com.travelhelper.dao;

import com.travelhelper.model.TravelModeSelected;

public interface TravelServiceDao {
	
	public int saveUserTravelSelection(TravelModeSelected travelPref);
	public int fetchDriveIdFromName(String driveName);
}
