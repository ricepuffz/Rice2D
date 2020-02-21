package de.ricepuffz.rice2d.math;

public class Line
{
	private Point point;
	private Vector vector;
	
	
	public Line(Point point, Vector vector)
	{
		this.point = point;
		this.vector = vector;
	}


	public Point intersectionPoint(Line line)
	{
		Point l1p1 = point;
		Point l1p2 = new Point(l1p1.x + vector.x, l1p1.y + vector.y);
		
		Point l2p1 = line.point;
		Point l2p2 = new Point(l2p1.x + line.vector.x, l2p1.y + line.vector.y);
		
		float a1 = l1p2.y - l1p1.y;
        float b1 = l1p1.x - l1p2.x;
        float c1 = a1 * l1p1.x + b1 * l1p1.y;
 
        float a2 = l2p2.y - l2p1.y;
        float b2 = l2p1.x - l2p2.x;
        float c2 = a2 * l2p1.x + b2 * l2p1.y;
 
        float delta = a1 * b2 - a2 * b1;
        
        return new Point((b2 * c1 - b1 * c2) / delta, (a1 * c2 - a2 * c1) / delta);
	}
	
	
	public Point point()
	{
		return point;
	}
	
	public Vector vector()
	{
		return vector;
	}
}
