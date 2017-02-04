package com.travelhelper.dao;

import com.travelhelper.model.FutureTravel;
import com.travelhelper.model.TravelModeSelected;

public interface TravelServiceDao {
	
	public int saveUserTravelSelection(TravelModeSelected travelPref);
	public int fetchDriveIdFromName(String driveName);
	public int saveFutureScheduledRequest(FutureTravel fTravel);
	
	public String retrieveUberAuthenticationToken(String code);
}
