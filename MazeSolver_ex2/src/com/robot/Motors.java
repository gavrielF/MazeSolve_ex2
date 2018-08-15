package com.robot;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;

public class Motors
{
	private NXTMotor _rightMotor;
	private NXTMotor _leftMotor;
	
	private final float trackWidth = 4.9f;
	private byte _parity = 1;
	private TachoPose lastTachoPose;
	private static final float TWO_PI = 6.284f;
	
	
	public Motors()
	{
		_rightMotor = new NXTMotor(MotorPort.B);
		_leftMotor = new NXTMotor(MotorPort.C);
		
		lastTachoPose = new TachoPose();
		
	}
	
	public void setPower(double left, double right)
	{
		if(left < 0)
			_leftMotor.backward();
		else
			_leftMotor.forward();
		
		if(right < 0)
			_rightMotor.backward();
		else
			_rightMotor.forward();
		
		
		_leftMotor.setPower((int)(Math.abs(left)));
		_rightMotor.setPower((int)(Math.abs(right)));

	}
	
	public TachoPose getPose() 
	{
		int lsamp = getLeftCount();
		int rsamp = getRightCount();

		int L_ticks = lsamp - lastTachoPose.getLeftTC();
		int R_ticks = rsamp - lastTachoPose.getRightTC();

		lastTachoPose.setLeftTC(lsamp);
		lastTachoPose.setRightTC(rsamp);

		float left_inches = (float) L_ticks / 45.0f;
		float right_inches = (float) R_ticks / 45.0f;

		float inches = (left_inches + right_inches) / 2.0f;
		float theta = lastTachoPose.getHeading()
				+ normalize(((left_inches - right_inches) / trackWidth));

		float normalizedTheta = normalize(theta);

		lastTachoPose.setY(lastTachoPose.getY()
				+ (inches * (float) Math.cos(normalizedTheta)));
		lastTachoPose.setX(lastTachoPose.getX()
				+ (inches * (float) Math.sin(normalizedTheta)));

		lastTachoPose.setHeading(normalizedTheta);

		return lastTachoPose;
	}


	float normalize(float theta) 
	{
	    float normalized = theta % TWO_PI;
	    normalized = (normalized + TWO_PI) % TWO_PI;
	    return normalized <= 3.142 ? normalized : normalized - TWO_PI;
	}
	
	public void setPose(TachoPose aPose) 
	{
		lastTachoPose = aPose;
	}

	private int getLeftCount() 
	{
		return _parity * _leftMotor.getTachoCount();
	}

	private int getRightCount() 
	{
		return _parity * _rightMotor.getTachoCount();
	}
	
	

}

