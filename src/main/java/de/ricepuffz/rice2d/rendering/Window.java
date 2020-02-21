package de.ricepuffz.rice2d.rendering;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Window
{
	private String title;
	private int width;
	private int height;
	
	private long windowID = 0;
	
	public Window(String title, int width, int height)
	{
		this.width = width;
		this.height = height;
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		windowID = glfwCreateWindow(width, height, title, 0, 0);
		if (windowID == 0)
		{
			System.err.println("Failed to create window '" + title + "'");
		}
		
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(windowID, (videoMode.width() - width) / 2 , (videoMode.height() - height) / 2);
		
		glfwSetWindowAttrib(windowID, GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
		
		glfwShowWindow(windowID);
		glfwMakeContextCurrent(windowID);
	}
	
	public void showCursor()
	{
		glfwSetInputMode(windowID, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
	}
	public void hideCursor()
	{
		glfwSetInputMode(windowID, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
	}
	
	public String getTitle() { return title; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public long id() { return windowID; }
}
