package Tasks;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.logger.Logger;
import com.robot.Motors;
import com.robot.Sensors;
import com.robot.Utils;
import com.robot.caliVals;

import controllers.PIDController;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;
import lejos.util.PilotProps;
import lejos.util.Stopwatch;


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
			return new Tasks_3(Utils.calival);
		}

		return null;
	}
}

class checkendLine {
	private Stopwatch watch = new Stopwatch();
	
	private Stopwatch watch2 = new Stopwatch();
	private int blackLine = 0;

	public checkendLine() {
		watch.reset();
		watch2.reset();
	}

	public boolean check() 
	{
		// do every 200 ms
		if (watch.elapsed() < 5000) 
		{
			return false;
		}

		if(watch2.elapsed() > 200)
		{
			int color = new Integer(Sensors.getLightSensorVal());
	
			if (color <= 40)
				blackLine++;
			
			watch2.reset();
		}
		if (blackLine >= 2)
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
			while (!Button.ESCAPE.isDown() && !Sensors.isExit()) 
			{
				if (Sensors.isWall()) 
				{
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
			
			motors.setPower(1, 1);

			// finish the controller task
			controllerl.finish();
		} 
		catch (Exception e) 
		{
			Logger.getInstance().logDebug("main - end get exception: " + e.getMessage());
		} 
	}
}

class Tasks_2 implements BaseTask 
{
	private int degree = -30;
	private int dist = 80;
	
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
		//readfromfile();
		
		//go to the begging
		backtobegging();
		
		LCD.clear();
		LCD.drawString("backtobegging end", 0, 3);
		waitAndMakeNoise(5); //wait 30 sec and wait for noise	
		
		//go to the middle point
		gotomiddle();
		
		LCD.clear();
		LCD.drawString("go to middle end", 0, 3);
		waitAndMakeNoise(10); //wait 30 sec and wait for noise	
	
		gotowall();
		LCD.clear();
		LCD.drawString("go to wall end", 0, 3);
			
		LCD.clear();
		LCD.drawString("end press enter", 0, 3);
		waitAndMakeNoise(5); //wait 30 sec and wait for noise
		
		Utils.waitForEnter();
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
	
	private void gotowall()
	{
			Motors motors = new Motors();
			
			motors.setPower(60, 60);
			Delay.msDelay(6 * 1000);
			
			motors.setPower(0,0);
			
			Tasks_3 t3 = new Tasks_3(Utils.calival);
			t3.execute();
			
		
		
	}
	
	private void gotomiddle()
	{
		//rotat
			Motors motors = new Motors();		
			motors.setPower(70, -20);
			Delay.msDelay(600);
			
			//move forword
			motors.setPower(60, 60);
			Delay.msDelay(6 * 1000);
		
			motors.setPower(0,0);
	}
	
	private void waitAndMakeNoise(int sec)
	{
		Sound.buzz();
		
		Sound.playSample(new File("Space_Alert3.wav"));
		
		Delay.msDelay(sec * 1000);
				
	}

}
class Tasks_3 implements BaseTask
{

	private PIDController controllerl = null;
	private caliVals _calival = null;

	private checkendLine lineChecker = null;
	
	private boolean _toOdy = false;

	public Tasks_3(caliVals calival)
	{
		if(calival != null)
			_calival = calival;
		
		else
			_calival = new caliVals(6, 12);
		
	}
	
	
	@Override
	public void execute() 
	{
		lineChecker = new checkendLine();

		Motors motors = new Motors();
		int powerAdd = 40;
		int wallCount = 0;

		try 
		{
			controllerl = new PIDController(_calival.s_low, _calival.s_high, _toOdy);

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