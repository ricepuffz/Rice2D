package de.ricepuffz.rice2d.animation;

import java.util.ArrayList;
import java.util.List;

public class Animation
{
	public List<Frame> frames = null;
	
	boolean looping = false;
	int currentFrameIndex = 0;
	int durationCounter = 1;
	
	boolean eventDone = false;
	
	
	public Animation()
	{
		frames = new ArrayList<Frame>();
	}
	
	public Animation(boolean looping)
	{
		this();
		this.looping = looping;
	}
	
	
	public void addFrame(Frame frame)
	{
		frames.add(frame);
	}
	
	public Frame getFrameAndNext()
	{
		Frame frame = frames.get(currentFrameIndex);
		
		if (durationCounter >= frame.duration())
		{
			if (looping)
				currentFrameIndex = currentFrameIndex + 1 >= frames.size() ? 0 : currentFrameIndex + 1;
			else if (currentFrameIndex + 1 <= frames.size())
				currentFrameIndex++;
			durationCounter = 1;
		} else {
			durationCounter++;
		}
			
		return frame;
	}
	
	public void toStart()
	{
		currentFrameIndex = 0;
		durationCounter = 1;
	}
	
	public int durationCounter()
	{
		return durationCounter;
	}
}
