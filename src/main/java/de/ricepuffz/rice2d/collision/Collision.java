package de.ricepuffz.rice2d.collision;

import java.util.ArrayList;
import java.util.List;

import de.ricepuffz.rice2d.math.Point;
import de.ricepuffz.rice2d.math.Rectangle;
import de.ricepuffz.rice2d.math.Vector;
import de.ricepuffz.rice2d.scene.object.CollisionBox;

public class Collision
{
	private Collision() {}
	
	
	public static boolean collisionTest(CollisionBox box1, CollisionBox box2)
	{
		if (box1.getRotationDegrees() != 0 && box2.getRotationDegrees() != 0)
			return unrotatedCollisionTest(box1, box2);
		return rotatedCollisionTest(box1, box2);
	}
	
	public static boolean unrotatedCollisionTest(CollisionBox box1, CollisionBox box2)
	{
		return unrotatedCollisionTest(box1.asRectangle(), box2.asRectangle());
	}
	
	public static boolean unrotatedCollisionTest(Rectangle rec1, Rectangle rec2)
	{
		float rec1X = rec1.vertices[0].x;
		float rec1Y = rec1.vertices[0].y;
		float rec1Width = rec1.width();
		float rec1Height = rec1.height();
		
		float rec2X = rec2.vertices[0].x;
		float rec2Y = rec2.vertices[0].y;
		float rec2Width = rec2.width();
		float rec2Height = rec2.height();
		
		if 	(rec1X < rec2X + rec2Width &&
			 rec1X + rec1Width > rec2X &&
			 rec1Y < rec2Y + rec2Height &&
			 rec1Y + rec1Height > rec2Y)
			return true;
		
		return false;
	}
	
	public static boolean rotatedCollisionTest(CollisionBox box1, CollisionBox box2)
	{
		return rotatedCollisionTest(box1.asRectangle(), box2.asRectangle());
	}
	
	public static boolean rotatedCollisionTest(Rectangle rec1, Rectangle rec2)
	{
		List<Vector> normals = edgeNormals(rec1);
		
		normals.addAll(edgeNormals(rec2));
		
		//TODO: Do not test duplicate axes
		
		for (Vector axis : normals)
		{
			if (!axisTest(axis, rec1, rec2))
				return false;
		}
		
		return true;
	}
	
	private static List<Vector> edgeNormals(Rectangle rec)
	{
		List<Vector> normals = new ArrayList<Vector>();
		Vector[] edges = rec.edges;
		
		for (int i = 0; i < 4; i++)
		{
			Vector vector = new Vector(-edges[i].y, edges[i].x);
			vector.normalize();
			normals.add(vector);
		}
			
		return normals;
	}
	
	private static boolean axisTest(Vector axis, Rectangle rec1, Rectangle rec2)
	{
		float[] rec1MinMax = projectionMinMax(rec1, axis);
		float[] rec2MinMax = projectionMinMax(rec2, axis);
		
		if (rec1MinMax[1] < rec2MinMax[0] || rec2MinMax[1] < rec1MinMax[0])
			return false;
		
		return true;
	}
	
	private static float[] projectionMinMax(Rectangle rec, Vector axis)
	{
		Point[] points = rec.vertices;
		float firstProjection = projectPointOnAxis(points[0], axis);
		float min = firstProjection;
		float max = firstProjection;
		
		for (int i = 1; i < 4; i++)
		{
			float projection = projectPointOnAxis(points[i], axis);
			if (projection < min)
				min = projection;
			if (projection > max)
				max = projection;
		}
		
		return new float[] { min, max };
	}
	
	private static float projectPointOnAxis(Point point, Vector axis)
	{
		return axis.dotProduct(new Vector(point));
	}
}
