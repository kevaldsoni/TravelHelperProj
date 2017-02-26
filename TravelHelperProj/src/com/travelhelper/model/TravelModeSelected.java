package com.travelhelper.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRAVEL_REQUEST")
public class TravelModeSelected {
	
	
	@Id
	@Column(name = "travel_request_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int travelRequestId;
	
	@Column (name = "user_id")
	private int userId;
	
	@Column(name = "START_POINT_X_COORDINATE")
	private String sourceLatitude;
	
	@Column (name = "START_POINT_Y_COORDINATE")
	private String sourceLongitude;
	
	@Column (name = "END_POINT_X_COORDINATE")
	private String destLatitude;
	
	@Column (name = "END_POINT_Y_COORDINATE")
	private String destLongitude;
	
	@Column (name = "REQUEST_TIMESTAMP")
	private Date requestTimeStamp;
	
	@Column (name = "TRAVEL_DRIVE_SELECTED")
	private int drive;
	
	@Column (name = "TRAVEL_DISTANCE_IN_MILES")
	private String distance;
	
	@Column (name = "TRAVEL_MODE_SELECTED")
	private int travelMode;
	
	@Column (name = "TRAVEL_DURATION")
	private int duration;
	
	@Column (name = "TRAVEL_STATUS")
	private String travelStatus;
	
	@Column (name = " user_selected_drive")
	private String userDrive;
	
	private int cost;
	
	@Column (name = " modename")
	private String modeName;
		
	public String getModeName() {
		return modeName;
	}
	public void setModeName(String modeName) {
		this.modeName = modeName;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getUserDrive() {
		return userDrive;
	}
	public void setUserDrive(String userDrive) {
		this.userDrive = userDrive;
	}
	
	public int getTravelMode() {
		return travelMode;
	}
	public void setTravelMode(int travelMode) {
		this.travelMode = travelMode;
	}
	public String getTravelStatus() {
		return travelStatus;
	}
	public void setTravelStatus(String travelStatus) {
		this.travelStatus = travelStatus;
	}
	public int getTravelRequestId() {
		return travelRequestId;
	}
	public void setTravelRequestId(int travelRequestId) {
		this.travelRequestId = travelRequestId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Date getRequestTimeStamp() {
		return requestTimeStamp;
	}
	public void setRequestTimeStamp(Date requestTimeStamp) {
		this.requestTimeStamp = requestTimeStamp;
	}
	
	public int getDrive() {
		return drive;
	}
	public void setDrive(int drive) {
		this.drive = drive;
	}
	public String getSourceLatitude() {
		return sourceLatitude;
	}
	public void setSourceLatitude(String sourceLatitude) {
		this.sourceLatitude = sourceLatitude;
	}
	public String getSourceLongitude() {
		return sourceLongitude;
	}
	public void setSourceLongitude(String sourceLongitude) {
		this.sourceLongitude = sourceLongitude;
	}
	public String getDestLatitude() {
		return destLatitude;
	}
	public void setDestLatitude(String destLatitude) {
		this.destLatitude = destLatitude;
	}
	public String getDestLongitude() {
		return destLongitude;
	}
	public void setDestLongitude(String destLongitude) {
		this.destLongitude = destLongitude;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
		
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String toString(){
		return "TravelModeSelected [drive=" + drive + ", sourceLatitude=" + sourceLatitude + ", sourceLongitude="+sourceLongitude+
				",destLatitude="+destLatitude+ ",destLongitude="+destLongitude+",distance="+distance+",duration="+duration+"]";
	}

}
