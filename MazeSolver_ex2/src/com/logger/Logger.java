package com.logger;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.LCD;

// use example
//	Logger.getInstance().logDebug("ggg7");
// Logger.getInstance().logDebug("gfggs" , 34);

public final class Logger
{
	private static Logger	instance	= null;

	private List<String>	m_list		= new ArrayList<String>();

	private Logger()
	{
	}

	public static synchronized Logger getInstance()
	{
		if (instance == null)
		{
			instance = new Logger();
		}
		return instance;
	}

	public void writeToFile()

	{
		int count = 0;
		FileOutputStream fileStream = null;
		try
		{
			fileStream = new FileOutputStream(new File("Test.txt"));
		}
		catch (Exception e)
		{
			LCD.drawString("Can't make a file", 0, 0);
			System.exit(1);
		}

		DataOutputStream dataStream = new DataOutputStream(fileStream);

		do
		{
			try
			{
				dataStream.writeBytes(String.valueOf(count));
				dataStream.writeBytes(" ");

				fileStream.flush();
				count++;
			}
			catch (IOException e)
			{
				LCD.drawString("Can't write to the file", 0, 1);
				System.exit(1);
			}
		} while (count < 100);

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

	public void write()
	{
		File file = new File("logFile.txt");

		try (FileOutputStream fop = new FileOutputStream(file))
		{

			// if file doesn't exists, then create it
			if (!file.exists())
			{
				file.createNewFile();
			}

			byte[] contentInBytes = "First Message \r\n".getBytes();
			fop.write(contentInBytes);

			for (String item : m_list)
			{
				contentInBytes = item.getBytes();
				fop.write(contentInBytes);
			}

			contentInBytes = "End Message \r\n".getBytes();
			fop.write(contentInBytes);

			fop.flush();
			fop.close();

		}
		catch (IOException e)
		{
			LCD.clear();
			LCD.drawString("cant make log file", 0, 0);
		}
	}

	public void logDebug(String msg)
	{
		if(m_list.size() < 500)
			m_list.add("Debug ---> " + msg + "\r\n");
	}

	public void logDebug(String msg, int num)
	{
		if(m_list.size() < 500)
			m_list.add("Debug ---> msg: " + msg + " Num: " + num + "\r\n");
	}

}
