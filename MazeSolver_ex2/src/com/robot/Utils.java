package com.robot;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;

public class Utils
{
	public static caliVals calival = null;
	
	public static void waitForEnter()
	{
		try
		{
			Button.ENTER.waitForPressAndRelease();
			Sound.beep();
		}
		catch(Exception ex)
		{
		}
	}
	
	public static void waitForLeft()
	{
		try
		{
			Button.LEFT.waitForPressAndRelease();
			Sound.beep();
		}
		catch(Exception ex)
		{
		}
	}
	
	public static caliVals getHighAndLow()
	{
		LCD.clear();
		LCD.drawString("Get low", 0, 2);
		Utils.waitForEnter();
		int low = new Integer(Sensors.getSonarVal());
		LCD.drawString("Get high", 0, 3);
		Utils.waitForEnter();
		int high = new Integer(Sensors.getSonarVal());
		LCD.drawString("l:" + low + ", h:" + high, 0, 4);	
		LCD.drawString("Next..", 0, 5);
		Utils.waitForEnter();
		LCD.clear();
		calival = new caliVals(low, high);
		return calival;
	}
}

