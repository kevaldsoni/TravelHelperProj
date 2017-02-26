package com.travelhelper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRAVEL_MODE")
public class TravelMode {
	
	@Id
	@Column (name ="TRAVEL_MODE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int modeId;
	
	@Column (name = "TRAVEL_MODE_NAME")
	private String modeName;

	public int getModeId() {
		return modeId;
	}

	public void setModeId(int modeId) {
		this.modeId = modeId;
	}

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	
	
}
