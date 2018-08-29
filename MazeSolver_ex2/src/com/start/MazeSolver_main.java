package com.start;

import com.logger.Logger;
import com.robot.Utils;
import Tasks.BaseTask;
import Tasks.Tasks;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.util.Delay;

public class MazeSolver_main 
{

	public static BaseTask task = null;
	public static Tasks _factoryTasks = new Tasks();
	private static int i = 0;
	
	public static void main(String[] args) 
	{
		try
		{
			//select the flow
			showOptions();	
			LCD.clear();
			LCD.drawString("Start..", 0, 1);
			Utils.waitForEnter();
			if(task != null)
				task.execute();
					
			LCD.drawString("finish main...", 0, 6);
			Utils.waitForEnter();		
		}
		finally 
		{
			Logger.getInstance().write();
		}
	
	}
	

	private static void showOptions()
	{	
		Delay.msDelay(200);
		
		Button.RIGHT.addButtonListener(new ButtonListener() 
		{
			@Override
			public void buttonReleased(Button b) 
			{
				i = i + 1;
				showSelection(i);
			}

			@Override
			public void buttonPressed(Button b) {		
			}
		});
		
		Button.LEFT.addButtonListener(new ButtonListener() 
		{
			@Override
			public void buttonReleased(Button b) 
			{
				i = i - 1;
				showSelection(i);
			}

			@Override
			public void buttonPressed(Button b) {		
			}
		});	
			
		showSelection(0);
		
		while (!Button.ENTER.isDown()) { }
	}
	
	private static void showSelection(int i)
	{
		i = i%3; // 0 or 1 the number of controller we want
		if (i<0) 
			i += 3;
		
		LCD.clear();
		LCD.drawString("Do maze map", 2, 2);
		LCD.drawString("Start task 2", 2, 3);
		LCD.drawString("Start test", 2, 4);
		LCD.drawString("{Enter to start}", 0, 5);
		
		LCD.drawString("->", 0, i+2);
		
		switch (i)
		{
			case 0:
				task = _factoryTasks.getTask("task1");
				break;
			case 1:
				task = _factoryTasks.getTask("task2");
				break;
			case 2:
				task = _factoryTasks.getTask("task3");
				break;
			default:
				LCD.drawString("error", 0, 4);
				break;
		}
	}

}

