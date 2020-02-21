package de.ricepuffz.rice2d.math;

public class Rectangle
{
	public Point[] vertices;
	public Vector[] edges;
	
	private float width, height;
	
	
	public Rectangle(Point[] vertices)
	{
		if (vertices.length != 4)
			throw new IllegalArgumentException("Rectangles must be instantiated with exactly 4 PointFs!");
		
		this.vertices = vertices;
		
		Point first = vertices[0];
		float lowestX = first.x;
		float highestX = first.x;
		float lowestY = first.y;
		float highestY = first.y;
		
		for (int i = 1; i < vertices.length; i++)
		{
			Point vertex = vertices[i];
			
			if (vertex.x < lowestX)
				lowestX = vertex.x;
			else if (vertex.x > highestX)
				highestX = vertex.x;
			if (vertex.y < lowestY)
				lowestY = vertex.y;
			else if (vertex.y > highestY)
				highestY = vertex.y;
		}
		
		width = highestX - lowestX;
		height = highestY - lowestY;
		
		calculateEdges();
	}
	
	public Rectangle(float x, float y, float width, float height)
	{
		vertices = new Point[] {
			new Point(x, y),
			new Point(x, y + height),
			new Point(x + width, y + height),
			new Point(x + width, y)
		};
		
		this.width = width;
		this.height = height;
		
		calculateEdges();
	}
	
	
	public void calculateEdges()
	{
		edges = new Vector[] {
				new Vector(vertices[0], vertices[1]),
				new Vector(vertices[1], vertices[2]),
				new Vector(vertices[2], vertices[3]),
				new Vector(vertices[3], vertices[0])
		};
	}
	
	public void rotate(float degrees)
	{
		degrees = (-degrees * 6.28F) / 360F;
		
		Point middle = globalMiddle();
		
		for (Point vertex : vertices)
		{
			vertex.x -= middle.x;
			vertex.y -= middle.y;
			
			float x = vertex.x * (float) Math.cos(degrees) - vertex.y * (float) Math.sin(degrees);
			vertex.y = vertex.x * (float) Math.sin(degrees) + vertex.y * (float) Math.cos(degrees);
			vertex.x = x;
			
			vertex.x += middle.x;
			vertex.y += middle.y;
		}
		
		calculateEdges();
	}
	
	public Point localMiddle()
	{
		Vector vector = new Vector(vertices[0], vertices[2]);
		vector.multiply(0.5F);
		
		return new Point(vector);
	}
	
	public Point globalMiddle()
	{
		Point localMiddle = localMiddle();
		
		return new Point(localMiddle.x + vertices[0].x, localMiddle.y + vertices[0].y);
	}
	
	public Rectangle boudingBox()
	{
		Point first = vertices[0];
		float lowestX = first.x;
		float highestX = first.x;
		float lowestY = first.y;
		float highestY = first.y;
		
		for (int i = 1; i < vertices.length; i++)
		{
			Point vertex = vertices[i];
			
			if (vertex.x < lowestX)
				lowestX = vertex.x;
			else if (vertex.x > highestX)
				highestX = vertex.x;
			if (vertex.y < lowestY)
				lowestY = vertex.y;
			else if (vertex.y > highestY)
				highestY = vertex.y;
		}
		
		return new Rectangle(first.x, first.y, highestX - lowestX, highestY - lowestY);
	}
	
	
	public float width()
	{
		return width;
	}
	
	public float height()
	{
		return height;
	}
}
