package com.robot;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;

public class Sensors
{

	public static final LightSensor _lightSensor = new LightSensor(SensorPort.S3);
	public static final UltrasonicSensor _sonarSensor = new UltrasonicSensor(SensorPort.S4);	
	
	public static final TouchSensor _touchSensor = new TouchSensor(SensorPort.S1);	
	
	
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
		return _touchSensor.isPressed();
	}

}
