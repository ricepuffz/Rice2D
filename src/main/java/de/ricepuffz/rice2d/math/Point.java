package de.ricepuffz.rice2d.math;

public class Point
{
	public float x;
	public float y;
	
	
	public Point(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point(Vector vector)
	{
		x = vector.x;
		y = vector.y;
	}
	
	
	public float distanceToLineSegment(LineSegment segment)
	{
		Point p1 = segment.point1();
		Point p2 = segment.point2();
		
		float lengthSquared = new Vector(p1, p2).magnitudeSquared();
		
		if (lengthSquared == 0)
			return distanceToPoint(p1);
		
		float t = Math.max(0, Math.min(1,
				new Vector(p1, this).dotProduct(new Vector(p1, p2).divide(lengthSquared))));
		
		Vector projection = new Vector(p1, p2).multiply(p1.x + t);
		
		return distanceToPoint(new Point(projection));
	}
	
	public float distanceToPoint(Point point)
	{
		return new Vector(this, point).magnitude();
	}
	
	public String asString()
	{
		return x + "/" + y;
	}
}
