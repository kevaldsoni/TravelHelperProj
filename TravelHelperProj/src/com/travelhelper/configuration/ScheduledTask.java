package com.travelhelper.configuration;

import java.util.Date;
import java.util.TimerTask;

public class ScheduledTask extends TimerTask {
	
	Date now;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		now = new Date();
		System.out.println("Time is now : "+now);
	}

}
