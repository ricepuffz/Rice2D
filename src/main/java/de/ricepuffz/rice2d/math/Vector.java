package de.ricepuffz.rice2d.math;

public class Vector
{
	public float x;
	public float y;
	
	
	public Vector(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector(Point from, Point to)
	{
		x = to.x - from.x;
		y = to.y - from.y;
	}
	
	public Vector(Point point)
	{
		x = point.x;
		y = point.y;
	}
	
	
	public Vector multiply(float value)
	{
		x *= value;
		y *= value;
		return this;
	}
	
	public Vector divide(float value)
	{
		x /= value;
		y /= value;
		return this;
	}
	
	public float magnitude()
	{
		return (float) Math.sqrt(magnitudeSquared());
	}
	
	public float magnitudeSquared()
	{
		return (float) (Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public Vector normalize()
	{
		this.divide(this.magnitude());
		return this;
	}
	
	public float dotProduct(Vector vector)
	{
		return this.x * vector.x + this.y * vector.y;
	}
	
	public String asString()
	{
		return x + "/" + y;
	}

	public Vector setLength(float length)
	{
		this.normalize();
		this.multiply(length);
		return this;
	}
}
