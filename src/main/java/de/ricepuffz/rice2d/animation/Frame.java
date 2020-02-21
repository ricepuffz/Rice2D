package de.ricepuffz.rice2d.animation;

public class Frame
{
	private String texture = null;
	private int duration = -1; //Amount of rendered frames until next frame becomes active (if present)
	
	public Frame(String texture, int duration)
	{
		this.texture = texture;
		this.duration = duration;
	}
	
	public String texture()
	{
		return texture;
	}
	
	public int duration()
	{
		return duration;
	}
}
