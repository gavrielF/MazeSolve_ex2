package controllers;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.robot.Motors;
import com.robot.Sensors;
import com.robot.TachoPose;
import com.robot.Utils;


import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.util.Stopwatch;

//======================================================================
//======================================================================
public class controllers 
{
	  
	   public BaseController getShape(String shapeType, int black, int white)
	   {
	      if(shapeType == null)
	      {
	         return null;
	      }		
	      if(shapeType.equalsIgnoreCase("PID_SONAR"))
	      {
	    	  return new PIDController(black, white); 
	      }
	      else if(shapeType.equalsIgnoreCase("PID-odey"))
	      {
	    	  return new PIDController(black, white); 
	      } 
	      
	      return null;
	   }
}



//======================================================================
//======================================================================
class lightMajer implements BaseController 
{
	float sensorData;

	public void run() {
		LCD.clear();
		sensorData = Sensors.getLightSensorVal();
		LCD.drawString("majer: " + sensorData, 0, 2);
		Utils.waitForEnter();
	}

	@Override
	public void finish() {

	}
}

//======================================================================
//======================================================================
class PIDController implements BaseController
{
	double leftSpeed, rightSpeed;
	double integral = 0;

	float sensorData;
	double middle = 0;
	double kp = 0;
	double ki = 0;
	double kd = 0;
	double turn = 0;
	double error = 0;

	double lastError = 0;
	double derivative = 0;

	int tp = 50;

	private Motors motors = new Motors();

	public PIDController(int black, int white) 
	{
		middle = (white + black) / 2;

		double Kc = 250;
		double pc = 0.2;
		double dt = 0.020;

		kp = (0.60) * (Kc);

		ki = (2 * (kp) * (dt)) / (pc);

		kd = ((kp) * (pc)) / ((8) * (dt));
	}

	public void run() 
	{
		sensorData = Sensors.getSonarVal();

		if (middle == sensorData || (error > 0 && (middle - sensorData) < 0)
				|| (error < 0 && (middle - sensorData) > 0))
			integral = 0;

		error = middle - sensorData;

		integral = (2/3) * integral + error;

		derivative = error - lastError;

		turn = (kp * error) + (ki * integral) + (kd * derivative);
		turn = turn / 100;

		leftSpeed = tp - turn;
		rightSpeed = tp + turn;

		motors.setPower(leftSpeed, rightSpeed);

		lastError = error;
	}

	@Override
	public void finish() 
	{
		motors.setPower(0, 0);
	}
}

//======================================================================
//======================================================================
class MovePosition
{
	private Motors _motors = null;
	
	private Stopwatch watch = new Stopwatch();
	
	private TachoPose lastTachoPose = new TachoPose();
	
	private List<Float> _listx = new ArrayList<Float>();
	private List<Float> _listy = new ArrayList<Float>();
	
	private int _elapsed = 400;
	
	final String fileName = "OdyPoints.txt";

	public MovePosition(Motors motors, int elapsed) 
	{
		_motors = motors;
		_elapsed = elapsed;
	}
	
	public void doTask()
	{
		//update the current position
		_motors.setPose(lastTachoPose);
				
		//take position every 1ms
		if(watch.elapsed() > _elapsed)
		{		
			lastTachoPose = _motors.getPose();
			_listx.add(lastTachoPose.getX());
			_listy.add(lastTachoPose.getY());
			watch.reset();
		}
	}

	public void printToFile() 
	{
		FileOutputStream fileStream = null;
		try 
		{
			fileStream = new FileOutputStream(new File(fileName));
		} 
		catch (Exception e)
		{
			LCD.drawString("Can't make a file", 0, 0);
			System.exit(1);
		}

		DataOutputStream dataStream = new DataOutputStream(fileStream);

		for (int i = 0; i < _listx.size(); i++) 
		{
			try
			{
				dataStream.writeBytes("(");
				dataStream.writeBytes(String.valueOf(_listx.get(i)));
				dataStream.writeBytes(", ");
				dataStream.writeBytes(String.valueOf(_listy.get(i)));
				dataStream.writeBytes("), ");
				fileStream.flush();
			} 
			catch (IOException e)
			{
				LCD.drawString("Can't write to the file", 0, 1);
				System.exit(1);
			}
		}
		try 
		{
			fileStream.close();
		} 
		catch (IOException e)
		{
			LCD.drawString("Can't save the file", 0, 1);
			System.exit(1);
		}
	}
}


