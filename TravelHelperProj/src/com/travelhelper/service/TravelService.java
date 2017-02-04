package com.travelhelper.service;

import com.travelhelper.model.FutureTravel;
import com.travelhelper.model.TravelModeSelected;

public interface TravelService {
	
	public int saveTravelModeSelected(TravelModeSelected travelData);
	
	public void pushFCMNotification(String clientDeviceID);
	
	public int saveFutureScheduledRequest(FutureTravel fTravel);
	
	public String getUberAuthentiationToken(String code); 

}
