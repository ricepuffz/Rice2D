package de.ricepuffz.rice2d;

import java.util.ArrayList;
import java.util.List;

public class ThreadCollection
{
	private List<Thread> threads = null;
	
	public ThreadCollection()
	{
		threads = new ArrayList<Thread>();
	}
	
	public void add(Thread thread)
	{
		threads.add(thread);
	}
	
	public void remove(Thread thread)
	{
		threads.remove(thread);
	}
	
	@SuppressWarnings("deprecation")
	void stopAll()
	{
		for (Thread thread : threads)
			thread.stop();
	}
}
