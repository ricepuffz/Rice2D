package de.ricepuffz.rice2d.scene.object;

import java.util.ArrayList;
import java.util.List;

import de.ricepuffz.rice2d.collision.Collision;
import de.ricepuffz.rice2d.collision.CollisionEvent;
import de.ricepuffz.rice2d.math.Rectangle;
import de.ricepuffz.rice2d.scene.Scene;
import de.ricepuffz.rice2d.scene.SceneObject;

public class CollisionBox extends SceneObject
{
	private float width, height;
	private Rectangle asRectangle;
	
	public CollisionBox(float width, float height)
	{
		super("empty", "default", "default");
		
		this.width = width;
		this.height = height;
		
		recalculateAsRectangle();
	}
	
	public List<CollisionEvent> getCollisions(Scene scene)
	{
		List<CollisionEvent> events = new ArrayList<CollisionEvent>();
		List<CollisionBox> potentialColliders = new ArrayList<CollisionBox>();
		
		for (SceneObject object : scene.getObjects())
		{
			if (object instanceof CollisionBox && object != this)
			{
				if (Collision.unrotatedCollisionTest(this.asRectangle.boudingBox(), ((CollisionBox) object).asRectangle.boudingBox()))
					potentialColliders.add((CollisionBox) object);
			}
		}
		
		for (CollisionBox potentialCollider : potentialColliders)
		{
			if (Collision.rotatedCollisionTest(this.asRectangle, potentialCollider.asRectangle))
				events.add(new CollisionEvent(this, potentialCollider));
		}
		
		return events;
	}
	
	public List<CollisionEvent> getCollisionsUnrotated(Scene scene)
	{		
		List<CollisionEvent> events = new ArrayList<CollisionEvent>();
		
		for (SceneObject object : scene.getObjects())
		{
			if (object instanceof CollisionBox && object != this)
			{
				if (Collision.unrotatedCollisionTest(asRectangle, ((CollisionBox) object).asRectangle))
					events.add(new CollisionEvent(this, (CollisionBox) object));
			}
		}
		
		return events;
	}
	
	public List<CollisionEvent> getCollisionsRotated(Scene scene)
	{
		List<CollisionEvent> events = new ArrayList<CollisionEvent>();
		List<CollisionBox> potentialColliders = new ArrayList<CollisionBox>();
		
		for (SceneObject object : scene.getObjects())
		{
			if (object instanceof CollisionBox && object != this)
			{
				if (Collision.unrotatedCollisionTest(this.asRectangle.boudingBox(), ((CollisionBox) object).asRectangle.boudingBox()))
					potentialColliders.add((CollisionBox) object);
			}
		}
		
		for (CollisionBox potentialCollider : potentialColliders)
		{
			if (Collision.rotatedCollisionTest(this.asRectangle, potentialCollider.asRectangle))
				events.add(new CollisionEvent(this, potentialCollider));
		}
		
		return events;
	}
	
	public void recalculateAsRectangle()
	{
		float[] position = this.getPos();
		asRectangle = new Rectangle(position[0] - (width / 2) * getScale(), position[1] - (height / 2) * getScale(), width * getScale(), height * getScale());
		asRectangle.rotate(this.getRotation());
	}
	
	public Rectangle asRectangle()
	{
		return asRectangle;
	}
}
