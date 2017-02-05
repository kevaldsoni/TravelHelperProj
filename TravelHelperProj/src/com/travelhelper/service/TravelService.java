package com.travelhelper.service;

import java.util.List;
import java.util.Map;

import com.travelhelper.model.FutureTravel;
import com.travelhelper.model.TravelModeSelected;

public interface TravelService {
	
	public int saveTravelModeSelected(TravelModeSelected travelData);
	
	public void pushFCMNotification(String clientDeviceID);
	
	public int saveFutureScheduledRequest(FutureTravel fTravel);
	
	public String getUberAuthentiationToken(String code);
	
	public List<TravelModeSelected> getPastTravelHistory(int userId);
	
	public List<FutureTravel> getPastFutureScheduleHistory(int userId);
	
	public Map<String,Long> fetchScheduledTravelSummaryBasedonDrive(int userId);
	
	public Map<String,Long> fetchTravelHistorySummaryBasedonDrive(int userId);
}
