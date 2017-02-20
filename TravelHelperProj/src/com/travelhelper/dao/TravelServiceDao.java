package com.travelhelper.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.travelhelper.model.FutureTravel;
import com.travelhelper.model.TravelModeSelected;

public interface TravelServiceDao {
	
	public int saveUserTravelSelection(TravelModeSelected travelPref);
	
	public int fetchDriveIdFromName(String driveName);
	
	public int saveFutureScheduledRequest(FutureTravel fTravel);
	
	public String retrieveUberAuthenticationToken(String code);
	
	public List<TravelModeSelected> getPastTravelHistory(int userId);
	
	public List<TravelModeSelected> getDateRangePastTravelHistory(int userId,Date startDate,Date endDate);
	
	public List<FutureTravel> getPastFutureScheduleHistory(int userId);
	
	public List<FutureTravel> getDateRangePastFutureScheduleHistory(int userId,Date startDate, Date endDate);
	
	public Map<String,Long> fetchScheduledTravelSummaryBasedonDrive(int userId);
	
	public Map<String,Long> fetchDateRangeScheduledTravelSummaryBasedonDrive(int userId,Date startDate, Date endDate);
	
	public Map<String,Long> fetchTravelHistorySummaryBasedonDrive(int userId);
	
	public Map<String,Long> fetchDateRangeTravelHistorySummaryBasedonDrive(int userId,Date startDate,Date endDate);
}
