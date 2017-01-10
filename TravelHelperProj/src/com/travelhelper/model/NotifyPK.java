package com.travelhelper.model;

import java.io.Serializable;

public class NotifyPK implements Serializable{
	
	protected int userId;
	
	protected String gcmRegId;
	
	public NotifyPK(){
		
	}
	
	public NotifyPK(int userId,String gcmId){
		this.userId = userId;
		this.gcmRegId = gcmId;
	}
	
}
