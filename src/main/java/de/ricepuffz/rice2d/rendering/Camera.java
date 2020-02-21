package de.ricepuffz.rice2d.rendering;

import org.joml.Matrix4f;
import org.joml.Vector3f;

//THE CAMERA POSITION VECTOR METHODS DO *NOT* REVERSE THE VALUES, IE A CAMERA.ADDPOSITION CALL WITH POSITIVE VALUES WOULD MOVE THE CAME DOWN AND LEFT!!
public class Camera
{
	private Vector3f position;
	private Matrix4f projection;
	
	public Camera(int width, int height)
	{
		position = new Vector3f(0, 0, 0);
		projection = new Matrix4f().setOrtho2D(-width / 2, width / 2, -height / 2, height / 2);
	}
	
	public void setPosition(Vector3f position)
	{
		this.position = position;
	}
	public void setPosition(float x, float y)
	{
		position.x = x;
		position.y = y;
	}
	
	public void addPosition(Vector3f position)
	{
		this.position.add(position);
	}
	public void addPosition(float x, float y)
	{
		position.x += x;
		position.y += y;
	}
	
	public Vector3f getPositionVector() { return position; }
	public float[] getPosition() { return new float[] { position.x, position.y }; }
	
	public Matrix4f getProjection()
	{
		Matrix4f target = new Matrix4f();
		Matrix4f pos = new Matrix4f().setTranslation(new Vector3f(-position.x, -position.y, position.z));
		
		target = projection.mul(pos, target);
		return target;
	}
	public String getProjectionString()
	{
		return getProjection().toString();
	}
}
