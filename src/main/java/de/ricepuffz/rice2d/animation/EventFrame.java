package de.ricepuffz.rice2d.animation;

import de.ricepuffz.rice2d.scene.SceneObject;

public abstract class EventFrame extends Frame
{
	public EventFrame(String texture, int duration)
	{
		super(texture, duration);
	}
	
	public abstract void event(SceneObject originObject);
}
