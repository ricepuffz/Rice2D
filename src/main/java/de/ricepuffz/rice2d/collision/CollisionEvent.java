package de.ricepuffz.rice2d.collision;

import de.ricepuffz.rice2d.scene.object.CollisionBox;

public class CollisionEvent
{
	public CollisionBox box1, box2;
	
	public CollisionEvent(CollisionBox box1, CollisionBox box2)
	{
		this.box1 = box1;
		this.box2 = box2;
	}
}
