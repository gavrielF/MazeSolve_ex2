package com.start;

import com.logger.Logger;
import com.robot.Motors;
import com.robot.Sensors;
import com.robot.Utils;

import controllers.PIDController;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.util.Delay;

public class LineFollwer 
{

	public static PIDController contruller = null;
//	public static controllers _factoryControlls = new controllers();
	public static int s_low = 0;
	public static int s_high = 0;
	
	
	
	public static void main(String[] args) 
	{
		Motors motors = new Motors();
		int powerAdd = 40;
		int wallCount = 0;
		
		try
		{		
			//get low and high
			getHighAndLow();
			
			contruller = new PIDController(s_low, s_high);
		//	contruller = _factoryControlls.getShape("sensor_test", s_low, s_high);
			
			//while loop		
			while (!Button.ESCAPE.isDown() && !Sensors.isExit())
			{				
				if(Sensors.isWall())
				{
					wallCount++;
					contruller.restart();	
					
					motors.setPower(-40, -40);
					Delay.msDelay(400);									
					
					motors.setPower(powerAdd + wallCount*10, -powerAdd);
					Delay.msDelay(250);
					
					motors.setPower(powerAdd, powerAdd);
					Delay.msDelay(350);
					
					motors.setPower(0, 0);					
				}
				else	
				{
					wallCount = 0;
					contruller.run();
				}
			}
			
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

