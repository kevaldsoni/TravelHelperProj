package com.travelhelper.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass (NotifyPK.class)
@Table(name = "USER_CLOUD_NOTIFICATION_ID")
public class GoogleNotification implements Serializable {
	
	@Id
	@Column (name = "USER_ID")
	private int userId;
	
	@Id
	@Column (name = "GCM_REGISTERATION_ID")
	private String gcmRegId;
	
	private int active;

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getGcmRegId() {
		return gcmRegId;
	}

	public void setGcmRegId(String gcmRegId) {
		this.gcmRegId = gcmRegId;
	}
	
	

}
