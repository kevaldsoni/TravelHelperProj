package com.travelhelper.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "FUTURE_SCHEDULED_TRAVEL")
public class FutureTravel {
	
	@Id
	@Column (name = "record_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int recordId;
	
	@Column (name = "user_id")
	private int userId;
	
	@Column (name = "START_POINT_X_COORDINATE")
	private String startLatitude;
	
	@Column (name = "START_POINT_Y_COORDINATE")
	private String startLongitude;
	
	@Column (name = "END_POINT_X_COORDINATE")
	private String endLatitude;
	
	@Column (name = "END_POINT_Y_COORDINATE")
	private String endLongitude;
	
	@Column (name = "PRE_NOTIFICATION_TIME_IN_MINUTES")
	private int preNotificationTime;
	
	@Column (name = "drive_id")
	private int travelDriveId;
	
	@Column (name = "drive_selected")
	private String travelDriveSelected;

	@Column (name = "NOTIFICATION_TIME")
	private Date notificationTime;
	
	@Column (name = "destination_reach_time")
	private Date destinationReachTime;
	
	@Column (name = "traveltime_expected")
	private int traveltimeExpected;
	
	@Column (name = "request_savetime")
	private Date requestSavetime;
	
	

	public Date getDestinationReachTime() {
		return destinationReachTime;
	}

	public void setDestinationReachTime(Date destinationReachTime) {
		this.destinationReachTime = destinationReachTime;
	}

	

	public int getTraveltimeExpected() {
		return traveltimeExpected;
	}

	public void setTraveltimeExpected(int traveltimeExpected) {
		this.traveltimeExpected = traveltimeExpected;
	}

	public Date getRequestSavetime() {
		return requestSavetime;
	}

	public void setRequestSavetime(Date requestSavetime) {
		this.requestSavetime = requestSavetime;
	}

	public String getTravelDriveSelected() {
		return travelDriveSelected;
	}

	public void setTravelDriveSelected(String travelDriveSelected) {
		this.travelDriveSelected = travelDriveSelected;
	}

	public Date getNotificationTime() {
		return notificationTime;
	}

	public void setNotificationTime(Date notificationTime) {
		this.notificationTime = notificationTime;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStartLatitude() {
		return startLatitude;
	}

	public void setStartLatitude(String startLatitude) {
		this.startLatitude = startLatitude;
	}

	public String getStartLongitude() {
		return startLongitude;
	}

	public void setStartLongitude(String startLongitude) {
		this.startLongitude = startLongitude;
	}

	public String getEndLatitude() {
		return endLatitude;
	}

	public void setEndLatitude(String endLatitude) {
		this.endLatitude = endLatitude;
	}

	public String getEndLongitude() {
		return endLongitude;
	}

	public void setEndLongitude(String endLongitude) {
		this.endLongitude = endLongitude;
	}

	public int getPreNotificationTime() {
		return preNotificationTime;
	}

	public void setPreNotificationTime(int preNotificationTime) {
		this.preNotificationTime = preNotificationTime;
	}

	public int getTravelDriveId() {
		return travelDriveId;
	}

	public void setTravelDriveId(int travelDriveId) {
		this.travelDriveId = travelDriveId;
	}
	public String toString(){
		return "FutureTravel [startLatitude=" + startLatitude + ", startLongitude="+startLongitude+
				",endLatitude="+endLatitude+ ",endLongitude="+endLongitude+",preNotificationTime="+preNotificationTime+
				",notificationTime="+notificationTime+",travelDriveSelected="+travelDriveSelected+"]";
	}
	
}
