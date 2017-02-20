package com.travelhelper.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travelhelper.model.FutureTravel;
import com.travelhelper.model.TravelModeSelected;

public interface TravelService {
	
	public int saveTravelModeSelected(TravelModeSelected travelData);
	
	public void pushFCMNotification(String clientDeviceID);
	
	public int saveFutureScheduledRequest(FutureTravel fTravel);
	
	public String getUberAuthentiationToken(String code);
	
	public List<TravelModeSelected> getPastTravelHistory(int userId);
	
	public List<TravelModeSelected> getDateRangePastTravelHistory(int userId,Date startDate,Date endDate);
	
	public List<FutureTravel> getPastFutureScheduleHistory(int userId);
	
	public List<FutureTravel> getDateRangePastFutureScheduleHistory(int userId,Date startDate,Date endDate);
	
	public Map<String,Long> fetchScheduledTravelSummaryBasedonDrive(int userId);
	
	public Map<String,Long> fetchDateRangeScheduledTravelSummaryBasedonDrive(int userId,Date startDate,Date endDate);
	
	public Map<String,Long> fetchTravelHistorySummaryBasedonDrive(int userId);
	
	public Map<String,Long> fetchDateRangeTravelHistorySummaryBasedonDrive(int userId,Date startDate,Date endDate);
	
	public void generateTravelHistroyPdf(List<TravelModeSelected> list,int userId,String name);
	
	public void fetchTravelHistoryDownload(HttpServletRequest request, HttpServletResponse response,int userId);
	
	public void generateScheduleHistroyPdf(List<FutureTravel> list,int userId,String name);
	
	public void fetchScheduleHistoryDownload(HttpServletRequest request, HttpServletResponse response,int userId);
}
