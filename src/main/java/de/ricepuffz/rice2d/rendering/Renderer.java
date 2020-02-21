package de.ricepuffz.rice2d.rendering;

import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import de.ricepuffz.rice2d.References;
import de.ricepuffz.rice2d.resource.Shader;
import de.ricepuffz.rice2d.scene.Scene;
import de.ricepuffz.rice2d.scene.SceneObject;

public class Renderer
{
	private long windowID;
	private Camera camera;
	
	private Vector4f clearColor = null;
	
	public Renderer(Window window)
	{
		this.windowID = window.id();
		camera = References.camera;
		
		clearColor = new Vector4f();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void renderScene(Scene scene)
	{
		if (scene == null)
		{
			System.err.println("Renderer ERROR: Couldn't render scene because none is present!");
			return;
		}
		
		GL11.glClearColor(clearColor.x, clearColor.y, clearColor.z, clearColor.w);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		for (String layer : scene.getLayersSorted())
		{
			ArrayList<String> objects = (ArrayList<String>) scene.getObjectsFromLayer(layer);
			for (String objectName : objects)
			{
				SceneObject object = scene.getObject(objectName);
				
				object.proceedAnimation();
				
				if (object.isVisible())
				{
					Matrix4f target = object.getObjectMatrix();
					
					Shader shader = object.getShader();
					shader.bind();
					shader.setUniform("sampler", 0);
					shader.setUniform("projection", camera.getProjection().mul(target));
					
					object.getTexture().bind(0);
					
					object.getModel().render();
				}
			}
		}
		
		GLFW.glfwSwapBuffers(windowID);
	}
	
	public void setBackgroundColor(float r, float g, float b)
	{
		if (r<0 || r>1 || g<0 || g>1 || b<0 || b>1)
		{
			System.err.println("Renderer ERROR: Invalid background color, values must be between 0 and 1");
			return;
		}
		
		clearColor = new Vector4f(r, g, b, 1);
	}
}
