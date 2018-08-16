package Tasks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Documented;

import com.logger.Logger;
import com.robot.Motors;
import com.robot.Sensors;
import com.robot.Utils;
import com.robot.caliVals;

import controllers.PIDController;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LCDOutputStream;
import lejos.nxt.Sound;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import lejos.util.PilotProps;
import lejos.util.Stopwatch;
import lejos.util.TextMenu;

//======================================================================
//======================================================================
public class Tasks {

	public BaseTask getTask(String taskType) {
		if (taskType == null) {
			return null;
		}
		if (taskType.equalsIgnoreCase("task1")) {
			return new Tasks_1();
		} else if (taskType.equalsIgnoreCase("task2")) {
			return new Tasks_2();
		} else if (taskType.equalsIgnoreCase("task3")) {
			return new Tasks_3();
		}

		return null;
	}
}

class checkendLine {
	private Stopwatch watch = new Stopwatch();
	private int blackLine = 0;

	public checkendLine() {
		watch.reset();
	}

	public boolean check() {
		// do every 200 ms
		if (watch.elapsed() < 5000) {
			return false;
		}

		LCD.drawString("checkendLine", 0, 3);
		Utils.waitForEnter();

		int color = new Integer(Sensors.getLightSensorVal());

		if (color < 35)
			blackLine++;

		if (blackLine >= 1)
			return true;

		return false;
	}
}

class Tasks_1 implements BaseTask {

	private PIDController controllerl = null;
	private caliVals calival = null;

	private checkendLine lineChecker = null;
	
	private boolean _toOdy = true;

	public Tasks_1()
	{
	}
	
	public Tasks_1(boolean toOdy)
	{
		_toOdy = toOdy;
	}
	
	@Override
	public void execute() {
		lineChecker = new checkendLine();

		Motors motors = new Motors();
		int powerAdd = 40;
		int wallCount = 0;

		try {
			// get low and high
			calival = Utils.getHighAndLow();

			controllerl = new PIDController(calival.s_low, calival.s_high, _toOdy);

			// while loop
			while (!Button.ESCAPE.isDown() && !Sensors.isExit()) {
				if (Sensors.isWall()) {
					wallCount++;
					controllerl.restart();

					motors.setPower(-40, -40);
					Delay.msDelay(400);

					motors.setPower(powerAdd + wallCount * 10, -powerAdd);
					Delay.msDelay(250);

					motors.setPower(powerAdd, powerAdd);
					Delay.msDelay(350);

					motors.setPower(0, 0);
				} else {
					wallCount = 0;
					controllerl.run();
				}

				if (lineChecker.check()) {
					break;
				}

			}

			// finish the controller task
			controllerl.finish();
		} catch (Exception e) {
			Logger.getInstance().logDebug("main - end get exception: " + e.getMessage());
		} 
	}
}

class Tasks_2 implements BaseTask 
{
	private int degree = 45;
	private int dist = 50;
	
	 private static final short [] note = {
			    2349,115, 0,5, 1760,165, 0,35, 1760,28, 0,13, 1976,23, 
			    0,18, 1760,18, 0,23, 1568,15, 0,25, 1480,103, 0,18, 1175,180, 0,20, 1760,18, 
			    0,23, 1976,20, 0,20, 1760,15, 0,25, 1568,15, 0,25, 2217,98, 0,23, 1760,88, 
			    0,33, 1760,75, 0,5, 1760,20, 0,20, 1760,20, 0,20, 1976,18, 0,23, 1760,18, 
			    0,23, 2217,225, 0,15, 2217,218};
	
	
	@Override
	public void execute() 
	{
		LCD.drawString("Tasks_2...", 0, 3);
		Utils.waitForEnter();

		//read the data from the file
		readfromfile();
		
		//go to the begging
		//backtobegging();
		
	//	waitAndMakeNoise(); //wait 30 sec and wait for noise	
		
		//go to the middle point
		//gotomiddle();
		
		//waitAndMakeNoise(); //wait 30 sec and wait for noise
		
		//go to the begging
		//backtobegging();
		
	//	waitAndMakeNoise(); //wait 30 sec and wait for noise
		
	}
	
	private void readfromfile()
	{
		  File data = new File("direction.txt");
	       int x = 0,y = 0;
	       
	       StringBuffer strBuffer = new StringBuffer(); 
	       StringBuffer strBuffer2 = new StringBuffer(); 
	       char ch;
	   
	       try 
	       {
	         InputStream is = new FileInputStream(data);
	         DataInputStream din = new DataInputStream(is);
	         //char ch = din.readChar();         
	         ch = (char)din.readByte();
	         while(ch != 's') 
	         {
	        	  if(Character.isDigit(ch))
	        		  strBuffer.append(ch);            
	             ch = (char)din.readByte();                   
	         }
	         
	         ch = (char)din.readByte();
	         while(ch != '\n') 
	         {
	        	  if(Character.isDigit(ch))
	        		  strBuffer2.append(ch);            
	             ch = (char)din.readByte();         	      
	         }
	         
	         din.close();
	       } 
	       catch (IOException ioe) 
	       {
	         LCD.drawString("read exception", 0, 0);
	       }
	       
	       String content = strBuffer.toString();
	       String content2 = strBuffer2.toString();
	       
	       x = Integer.parseInt(content);
	       y = Integer.parseInt(content2);
	       
	       LCD.drawInt(x, 0, 1);
	       LCD.drawInt(y, 0, 2);
	       Utils.waitForEnter();
	}
	
	private void backtobegging()
	{
		Tasks_1 t1 = new Tasks_1(false);
		t1.execute();
	}
	
	private void gotomiddle()
	{
		try
		{
			PilotProps pp = new PilotProps();
	    	pp.loadPersistentValues();
	    	float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "4.32"));
	    	float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "16.35"));
	    	RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "C"));
	    	RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "B"));
	    	boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE,"false"));
	    	
	        DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor, reverse);
			
			pilot.rotate(degree);
			pilot.travel(dist);
		}
		catch (IOException e) 
		{
			
		}
	}
	
	private void waitAndMakeNoise()
	{
		Sound.buzz();
		Delay.msDelay(30 * 1000);
//		int val = 0;
//		LCD.clear();
//		LCD.drawString("start gg...", 0, 1);
//		Stopwatch watch = new Stopwatch();
//		while (watch.elapsed() < 30  * 1000)
//		{
//			val = Sensors.getSoundVal();
//			LCD.drawString("val = " + val, 0, 2);
//			if(val >= 94)
//			{
//				LCD.drawString("breaing gg..." + val, 0, 3);
//				Utils.waitForEnter();
//				break;
//			}
//		}
				
	}

}

class Tasks_3 implements BaseTask {

	@Override
	public void execute() {

		LCD.drawString("Tasks_3...", 0, 3);
		Utils.waitForEnter();
	}

}
