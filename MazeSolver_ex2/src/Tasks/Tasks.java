package Tasks;

import com.logger.Logger;
import com.robot.Motors;
import com.robot.Sensors;
import com.robot.Utils;
import com.robot.caliVals;

import controllers.PIDController;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.util.Delay;

//======================================================================
//======================================================================
public class Tasks 
{
  
   public BaseTask getTask(String taskType)
   {
      if(taskType == null)
      {
         return null;
      }		
      if(taskType.equalsIgnoreCase("task1"))
      {
    	  return new Tasks_1(); 
      }
      else if(taskType.equalsIgnoreCase("task2"))
      {
    	  return new Tasks_2(); 
      } 
      else if(taskType.equalsIgnoreCase("task3"))
      {
    	  return new Tasks_3(); 
      } 
      
      return null;
   }
}

class Tasks_1 implements BaseTask 
{

	private PIDController controllerl = null;
	private caliVals calival = null;
	
	@Override
	public void execute()
	{
		Motors motors = new Motors();
		int powerAdd = 40;
		int wallCount = 0;
		
		try
		{		
			//get low and high
			calival = Utils.getHighAndLow();
			
			controllerl = new PIDController(calival.s_low, calival.s_high);
			
			//while loop		
			while (!Button.ESCAPE.isDown() && !Sensors.isExit())
			{				
				if(Sensors.isWall())
				{
					wallCount++;
					controllerl.restart();	
					
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
					controllerl.run();
				}
			}
			
			//finish the controller task
			controllerl.finish();
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

}

class Tasks_2 implements BaseTask 
{

	@Override
	public void execute()
	{
		LCD.drawString("Tasks_2...", 0, 3);
		Utils.waitForEnter();
	}

}

class Tasks_3 implements BaseTask 
{

	@Override
	public void execute()
	{
	
		LCD.drawString("Tasks_3...", 0, 3);
		Utils.waitForEnter();
	}

}
