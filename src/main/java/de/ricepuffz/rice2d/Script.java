package de.ricepuffz.rice2d;

import de.ricepuffz.rice2d.scene.SceneObject;

public abstract class Script
{
	protected SceneObject executor = null;
	
	public Script(SceneObject executor)
	{
		this.executor = executor;
	}
	
	public abstract void init();
	public abstract void onFrame();
	public abstract void onTick();
}
