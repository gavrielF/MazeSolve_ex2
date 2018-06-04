package com.robot;

import com.logger.Logger;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;

public class Sensors
{

	public static final LightSensor _lightSensor = new LightSensor(SensorPort.S3);
	public static final UltrasonicSensor _sonarSensor = new UltrasonicSensor(SensorPort.S4);	
	
	public static final TouchSensor _touchSensor = new TouchSensor(SensorPort.S1);	
	public static final TouchSensor _touchSensorWall = new TouchSensor(SensorPort.S2);	
	
	
	public static int getLightSensorVal()
	{
		return _lightSensor.readValue();
	}
	
	public static int getLightSensorVal_normal()
	{
		return _lightSensor.getNormalizedLightValue();
	}
	
	public static int getSonarVal()
	{
		return _sonarSensor.getDistance();
	}
	
	public static boolean isExit()
	{
		Logger.getInstance().logDebug("Exit button was press");
		return _touchSensor.isPressed();
	}
	
	public static boolean isWall()
	{
		return _touchSensorWall.isPressed();
	}

}
