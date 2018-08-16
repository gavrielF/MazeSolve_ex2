package com.robot;

import com.logger.Logger;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;

public class Sensors
{

	public static final LightSensor _lightSensor = new LightSensor(SensorPort.S3);
	public static final UltrasonicSensor _sonarSensor = new UltrasonicSensor(SensorPort.S4);	
	
	public static final SoundSensor sound = new SoundSensor(SensorPort.S1);
	
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
		//_sonarSensor.ping();
		//Delay.msDelay(20);
		return _sonarSensor.getDistance();
	}
	
	public static int getSoundVal()
	{
		//_sonarSensor.ping();
		//Delay.msDelay(20);
		return sound.readValue();
	}
	
	public static boolean isExit()
	{
		
		return false;
	}
	
	public static boolean isWall()
	{
		return _touchSensorWall.isPressed();
	}

}
