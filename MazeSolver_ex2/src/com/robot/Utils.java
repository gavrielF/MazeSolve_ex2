package com.robot;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;

public class Utils
{
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
}

