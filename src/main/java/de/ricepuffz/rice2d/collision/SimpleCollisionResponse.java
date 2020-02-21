package de.ricepuffz.rice2d.collision;

import java.util.ArrayList;
import java.util.List;

import de.ricepuffz.rice2d.math.Line;
import de.ricepuffz.rice2d.math.Point;
import de.ricepuffz.rice2d.math.Rectangle;
import de.ricepuffz.rice2d.math.Vector;
import de.ricepuffz.rice2d.scene.object.CollisionBox;

public class SimpleCollisionResponse
{
	public static Vector moveVectorWithoutWeights(CollisionBox movingBox, Vector desiredMovement, CollisionBox staticBox)
	{
		Vector projectionAxis = new Vector(-desiredMovement.y, desiredMovement.x);
		
		List<Point> movingHull = forwardHull(movingBox.asRectangle(), projectionAxis, desiredMovement);
		List<Point> staticHull = forwardHull(staticBox.asRectangle(), projectionAxis, desiredMovement);
		
		float newDirectionLength = hullDistanceOverDirection(movingHull, staticHull, desiredMovement);
		
		desiredMovement.setLength(newDirectionLength);
		
		return desiredMovement;
	}
	
	public static List<Point> forwardHull(Rectangle rec, Vector axis, Vector direction)
	{
		List<Point> hullVertices = new ArrayList<Point>();
		
		float minVal, maxVal;
		Point minPoint, maxPoint;
		
		Point[] vertices = rec.vertices;
		
		float firstDotP = axis.dotProduct(new Vector(vertices[0]));
		minVal = firstDotP;
		maxVal = firstDotP;
		minPoint = vertices[0];
		maxPoint = vertices[0];
		
		for (int i = 1; i < vertices.length; i++)
		{
			float dotP = axis.dotProduct(new Vector(vertices[i]));
			
			if (dotP < minVal)
			{
				minVal = dotP;
				minPoint = vertices[i];
			} else if (dotP > maxVal) {
				maxVal = dotP;
				maxPoint = vertices[i];
			}
		}
		
		hullVertices.add(maxPoint);
		
		for (Point vertex : vertices)
		{
			if (vertex != minPoint && vertex != maxPoint)
			{
				Vector maxToVertex = new Vector(maxPoint, vertex);
				
				if (maxToVertex.dotProduct(direction) >= 0)
					hullVertices.add(vertex);
			}
		}
		
		hullVertices.add(minPoint);
		
		return hullVertices;
	}
	
	private static float hullDistanceOverDirection(List<Point> hull1, List<Point> hull2, Vector direction)
	{
		Vector[] hull1Edges = new Vector[hull1.size() - 1];
		Vector[] hull2Edges = new Vector[hull2.size() - 1];
		
		for (int i = 0; i < hull1Edges.length; i++)
			hull1Edges[i] = new Vector(hull1.get(i), hull1.get(i+1));
		
		for (int i = 0; i < hull2Edges.length; i++)
			hull2Edges[i] = new Vector(hull2.get(i), hull2.get(i+1));
		
		float lowestDistance = pointToHullDistanceOverDirection(hull1.get(0), hull1, hull1Edges, direction);
		
		for (int i = 1; i < hull1.size(); i++)
		{
			float distance = pointToHullDistanceOverDirection(hull1.get(i), hull1, hull1Edges, direction);
			
			if (distance < lowestDistance)
				lowestDistance = distance;
		}
		
		for (int i = 0; i < hull2.size(); i++)
		{
			float distance = pointToHullDistanceOverDirection(hull2.get(i), hull2, hull2Edges, direction);
			
			if (distance < lowestDistance)
				lowestDistance = distance;
		}
		
		return lowestDistance;
	}
	
	private static float pointToHullDistanceOverDirection(Point point, List<Point> points, Vector[] hullEdges, Vector direction)
	{
		Line pointAndDirection = new Line(point, direction);
		
		Point firstIntersectionPoint = pointAndDirection.intersectionPoint(new Line(points.get(0), hullEdges[0]));
		float lowestDistance = new Vector(point, firstIntersectionPoint).magnitudeSquared();
		
		for (int i = 1; i < hullEdges.length; i++)
		{
			Point intersectionPoint = pointAndDirection.intersectionPoint(new Line(points.get(i), hullEdges[i]));
			float distance = new Vector(point, intersectionPoint).magnitudeSquared();
			
			if (((Float) lowestDistance).isNaN())
				lowestDistance = distance;
			else if (distance < lowestDistance)
				lowestDistance = distance;
		}
		
		return (float) Math.sqrt(lowestDistance);
	}
}
