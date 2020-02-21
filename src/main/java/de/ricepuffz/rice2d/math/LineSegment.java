package de.ricepuffz.rice2d.math;

public class LineSegment
{
	private Point point1;
	private Point point2;
	
	
	public LineSegment(Point point1, Point point2)
	{
		this.point1 = point1;
		this.point2 = point2;
	}
	
	public LineSegment(Point point, Vector vector)
	{
		point1 = point;
		point2 = new Point(point1.x + vector.x, point1.y + vector.y);
	}
	
	
	public Point point1()
	{
		return point1;
	}
	
	public Point point2()
	{
		return point2;
	}
}
