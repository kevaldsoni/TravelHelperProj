package com.travelhelper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRAVEL_DRIVE")
public class TravelDrive {
	
	@Id
	@Column (name ="DRIVE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int driveId;
	
	@Column (name = "DRIVE_NAME")
	private String driveName;

	public int getDriveId() {
		return driveId;
	}

	public void setDriveId(int driveId) {
		this.driveId = driveId;
	}

	public String getDriveName() {
		return driveName;
	}

	public void setDriveName(String driveName) {
		this.driveName = driveName;
	}
	
	
}
