package de.ricepuffz.rice2d.scene;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import de.ricepuffz.rice2d.References;
import de.ricepuffz.rice2d.Script;
import de.ricepuffz.rice2d.animation.Animation;
import de.ricepuffz.rice2d.animation.EventFrame;
import de.ricepuffz.rice2d.animation.Frame;
import de.ricepuffz.rice2d.resource.Shader;
import de.ricepuffz.rice2d.resource.Texture;
import de.ricepuffz.rice2d.resource.model.Model;
import de.ricepuffz.rice2d.scene.object.CollisionBox;
import de.ricepuffz.rice2d.scene.object.Sprite;

public class SceneObject
{
	protected float scale;
	
	protected Vector3f pos = null;
	protected int wholeRotations = 0;
	protected float rotation = 0F;
	private Matrix4f objectMatrix = null;
	
	private Texture texture;
	private Model model;
	private Shader shader;
	
	private Animation currentAnimation = null;
	
	private List<Script> scripts;
	
	private SceneObject parent = null;
	private List<SceneObject> children = null;
	
	private boolean visible = true;
	
	public SceneObject(String texture, String model, String shader)
	{
		scale = 1F;
		pos = new Vector3f(0, 0 ,0);
		
		this.texture = References.resourceManager.getTexture(texture);
		this.model = References.resourceManager.getModel(model);
		this.shader = References.resourceManager.getShader(shader);
		
		scripts = new ArrayList<Script>();
		children = new ArrayList<SceneObject>();
		
		updateObjectMatrix();
	}
	
	
	
	public void proceedAnimation()
	{
		if (currentAnimation == null)
			return;
		
		Frame frame = currentAnimation.getFrameAndNext();
		
		this.setTexture(frame.texture());
		
		if (frame instanceof EventFrame && currentAnimation.durationCounter() == 1)
			((EventFrame) frame).event(this);
	}
	
	
	private Vector3f getRenderScaleVector()
	{
		int width = texture.getWidth();
		int height = texture.getHeight();
		
		if (width == height)
		{
			float renderScale = width*scale;
			return new Vector3f(renderScale, renderScale, 0F);
		} else {
			if (width < height)
				return new Vector3f(width*scale, width%height*scale, 0F);
			else
				return new Vector3f(height%width*scale, height*scale, 0F);
		}
	}
	
	
	public void setScale(float scale)
	{
		this.scale = scale;
		updateObjectMatrix();
	}
	public float getScale()
	{
		return scale;
	}
	
	protected void updateObjectMatrix()
	{
		float[] getPos = getPos();
		Vector3f position = new Vector3f(getPos[0], getPos[1], pos.z);
		
		objectMatrix = new Matrix4f()
				.translate(position)
				.scale(getRenderScaleVector())
				.rotate((-getRotation() * 6.28F) / 360F, 0F, 0F, 1F);
		
		if (this instanceof CollisionBox)
			((CollisionBox) this).recalculateAsRectangle();
		
		for (SceneObject child : children)
		{
			child.updateObjectMatrix();
			
			if (child instanceof CollisionBox)
			{
				child.setScale(getScale());
				((CollisionBox) child).recalculateAsRectangle();
			}
		}
	}
	
	public void rotate(float degrees)
	{
		float newRotation = rotation + degrees;
		
		if (newRotation >= 360)
		{
			wholeRotations += (int) (newRotation / 360);
			rotation = newRotation % 360;
		} else if (newRotation < 0) {
			wholeRotations -= (int) (1 - degrees / 360);
			rotation = 360 + newRotation % 360;
		} else {
			rotation += degrees;
		}
		
		updateObjectMatrix();
	}
	public void setRotation(float degrees)
	{
		if (degrees >= 360)
		{
			wholeRotations = (int) (degrees / 360);
			rotation = degrees % 360;
		} else if (degrees < 0) {
			wholeRotations = (int) (degrees / 360 - 1);
			rotation = 360 + degrees % 360;
		} else {
			rotation = degrees;
		}
		
		updateObjectMatrix();
	}
	public float getRotation()
	{
		if (parent == null)
			return getLocalRotation();
		return rotation + wholeRotations * 360 + parent.getRotation();
	}
	public float getLocalRotation()
	{
		return rotation + wholeRotations * 360;
	}
	public float getRotationDegrees()
	{
		if (parent == null)
			return getLocalRotationDegrees();
		return (rotation + parent.getRotationDegrees()) % 360;
	}
	public float getLocalRotationDegrees()
	{
		return rotation;
	}
	public int getWholeRotations()
	{
		if (parent == null)
			return getLocalWholeRotations();
		return wholeRotations + parent.wholeRotations;
	}
	public int getLocalWholeRotations()
	{
		return wholeRotations;
	}
	
	
	public Vector3f getPosVector()
	{
		if (parent == null)
			return getLocalPosVector();
		return new Vector3f(pos.x + parent.pos.x, pos.y + parent.pos.y, pos.z + parent.pos.z);
	}
	
	public Vector3f getLocalPosVector()
	{
		return new Vector3f(pos.x, pos.y, pos.z);
	}
	
	public float[] getPos()
	{
		if (parent == null)
			return getLocalPos();
		return new float[] { pos.x + parent.pos.x, pos.y + parent.pos.y };
	}
	
	public float[] getLocalPos()
	{
		return new float[] { pos.x, pos.y };
	}
	
	public void setPosWithVector(Vector3f offset)
	{
		pos = offset;
		updateObjectMatrix();
	}
	public void setPos(float x, float y)
	{
		pos = new Vector3f(x, y, pos.z);
		updateObjectMatrix();
	}
	public void setPos(float[] pos)
	{
		setPos(pos[0], pos[1]);
	}
	public void addPos(Vector3f offset)
	{
		pos.add(offset);
		updateObjectMatrix();
	}
	public void addPos(float x, float y)
	{
		pos.add(new Vector3f(x, y, 0));
		updateObjectMatrix();
	}
	
	public void setTexture(String texture)
	{
		this.texture = References.resourceManager.getTexture(texture);
		
		if (this instanceof Sprite)
			this.setModel(Sprite.modelName(texture));
		
		updateObjectMatrix();
	}
	
	public void setModel(String model)
	{
		this.model = References.resourceManager.getModel(model);
	}
	
	public void setLayer(String layer, Scene scene)
	{
		if (!scene.objectList.contains(this))
			System.err.println("SceneObject ERROR: Couldn't set layer of an object because it doesn't exist in the scene '" + scene.getName() + "'!");
		scene.setLayer(scene.objectName(this), layer);
	}
	
	
	public void addScript(Class<? extends Script> script)
	{
		try {
			Script newScript = script.getDeclaredConstructor(SceneObject.class).newInstance(this);
			scripts.add(newScript);
			newScript.init();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public List<Script> scripts()
	{
		return scripts;
	}
	
	
	public Animation currentAnimation()
	{
		return currentAnimation;
	}
	
	public void playAnimation(Animation animation)
	{
		currentAnimation = animation;
	}
	
	public void stopAnimation()
	{
		if (currentAnimation == null)
		{
			System.out.println("SceneObject WARN: Tried to stop animation for an object that had no animation playing!");
			return;
		}
		currentAnimation.toStart();
		currentAnimation = null;
	}
	
	
	public void setParent(SceneObject object)
	{
		if (parent != null)
			parent.children.remove(this);
		
		parent = object;
		parent.children.add(this);
		
		updateObjectMatrix();
	}
	
	public void removeParent()
	{
		setParent(null);
		
		updateObjectMatrix();
	}
	
	public SceneObject getParent()
	{
		return parent;
	}
	
	public List<SceneObject> getChildren()
	{
		return children;
	}
	
	
	public Texture getTexture() { return texture; }
	public Model getModel() { return model; }
	public Shader getShader() { return shader; }
	
	public boolean isVisible() { return visible; }
	public void setVisible(boolean visible) { this.visible = visible; }
	
	public Matrix4f getObjectMatrix() { return objectMatrix; }
}











