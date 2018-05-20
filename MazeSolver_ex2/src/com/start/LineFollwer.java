package com.start;

import com.logger.Logger;
import com.robot.Sensors;
import com.robot.Utils;

import controllers.BaseController;
import controllers.controllers;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;

public class LineFollwer 
{

	public static BaseController contruller = null;
	public static controllers _factoryControlls = new controllers();
	public static int s_low = 0;
	public static int s_high = 0;
	
	public static void main(String[] args) 
	{
		try
		{		
			//get low and high
			getHighAndLow();
			
			contruller = _factoryControlls.getShape("PID_SONAR", s_low, s_high);
			
			//while loop		
			while (!Button.ESCAPE.isDown() && !Sensors.isExit())
				contruller.run();
			
			//finish the controller task
			contruller.finish();
		} 
		catch (Exception e) 
		{
			Logger.getInstance().logDebug("main - end get exception: " + e.getMessage());
		} 
		finally
		{
			Logger.getInstance().write();
		}
	}
	
	private static void getHighAndLow()
	{
		LCD.clear();
		LCD.drawString("get low", 0, 2);
		Utils.waitForEnter();
		s_low = new Integer(Sensors.getSonarVal());
		LCD.drawString("get high", 0, 3);
		Utils.waitForEnter();
		s_high = new Integer(Sensors.getSonarVal());
		LCD.drawString("l:" + s_low + ", h:" + s_high, 0, 4);	
		LCD.drawString("Next..", 0, 5);
		Utils.waitForEnter();			
	}

}

