package de.ricepuffz.rice2d;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import de.ricepuffz.rice2d.audio.SoundManager;
import de.ricepuffz.rice2d.input.InputManager;
import de.ricepuffz.rice2d.rendering.Camera;
import de.ricepuffz.rice2d.rendering.Renderer;
import de.ricepuffz.rice2d.rendering.Window;
import de.ricepuffz.rice2d.resource.ResourceManager;
import de.ricepuffz.rice2d.scene.SceneManager;
import de.ricepuffz.rice2d.scene.SceneObject;

public abstract class Engine
{
	private String GAME_NAME;
	protected Window window = null;
	
	private Camera camera = null;
	private Renderer renderer = null;
	
	private int ticksPerSecond = 20;
	private int framesPerSecond = 60;
	private int tickMillisOffset = (int) 1000 / ticksPerSecond;
	private int frameMillisOffset = (int) 1000 / framesPerSecond;
	private long lastFrameMillis = 0;
	private long lastTickMillis = 0;
	private long frameCount = 0;
	private long tickCount = 0;
	
	private boolean consoleFPSCounter = false;
	
	public SceneManager sceneManager = null;
	public ResourceManager resourceManager = null;
	public InputManager inputManager = null;
	public SoundManager soundManager = null;
	
	public Engine()
	{
		this("Rice2D Game");
	}
	
	public Engine(String gameName)
	{
		this(gameName, 640, 480);
	}
	
	public Engine(String gameName, int windowWidth, int windowHeight)
	{
		GAME_NAME = gameName;
		
		if (!GLFW.glfwInit())
		{
			throw new IllegalStateException("Failed to initialize GLFW!");
		}
		
		window = new Window(gameName, windowWidth, windowHeight);
		References.window = window;
		
		GL.createCapabilities();
		
		camera = new Camera(window.getWidth(), window.getHeight());
		References.camera = camera;
		
		sceneManager = new SceneManager();
		References.sceneManager = sceneManager;
		resourceManager = new ResourceManager();
		References.resourceManager = resourceManager;
		inputManager = new InputManager(window.id());
		References.inputManager = inputManager;
		soundManager = new SoundManager();
		References.audioManager = soundManager;
		References.threadCollection = new ThreadCollection();
		
		renderer = new Renderer(window);
		
		gameInit();
		
		
		lastTickMillis = System.currentTimeMillis();
		lastFrameMillis = lastTickMillis;
		
		int framesLastTwoSeconds = 0;
		long nextFPSCheck = System.currentTimeMillis() + 2000;
		
		while(!GLFW.glfwWindowShouldClose(window.id()))
		{
			long currentTime = System.currentTimeMillis();
			if (currentTime - tickMillisOffset > lastTickMillis)
			{
				tick();
				lastTickMillis = currentTime;
			}
			if (currentTime - frameMillisOffset > lastFrameMillis)
			{
				frame();
				if (consoleFPSCounter)
				{
					framesLastTwoSeconds++;
					if (currentTime >= nextFPSCheck)
					{
						System.out.println("FPS: " + framesLastTwoSeconds / 2);
						framesLastTwoSeconds = 0;
						nextFPSCheck = currentTime + 2000;
					}
				}
				lastFrameMillis = currentTime;
			}
			
			//quit();
		}
		
		soundManager.destroyAudioManager();
	}
	
	
	private void tick()
	{
		onTick();
		
		for (SceneObject object : sceneManager.getCurrentScene().getObjects())
		{
			if (object != null)
			{
				for (Script script : object.scripts())
					script.onTick();
			}
		}
		
		tickCount++;
	}
	
	private void frame()
	{
		GLFW.glfwPollEvents();
		
		renderer.renderScene(sceneManager.getCurrentScene());
		
		onFrame();
		
		for (SceneObject object : sceneManager.getCurrentScene().getObjects())
		{
			if (object != null)
			{
				for (Script script : object.scripts())
					script.onFrame();
			}
		}
		
		inputManager.updatePressedLastFrame();
		
		frameCount++;
	}
	
	
	protected void quit()
	{
		GLFW.glfwSetWindowShouldClose(window.id(), true);
		References.threadCollection.stopAll();
	}
	
	protected abstract void gameInit();
	protected abstract void onTick();
	protected abstract void onFrame();
	
	
	public void setBackgroundColor(float r, float g, float b)
	{
		renderer.setBackgroundColor(r, g, b);
	}
	
	public String getGameName() { return GAME_NAME; }
	
	protected void setTicksPerSecond(int ticksPerSecond)
	{
		this.ticksPerSecond = ticksPerSecond;
		tickMillisOffset = (int) 1000 / ticksPerSecond;
	}
	protected void setFramesPerSecond(int framesPerSecond)
	{
		this.framesPerSecond = framesPerSecond;
		frameMillisOffset = (int) 1000 / framesPerSecond;
	}
	protected int ticksPerSecond()
	{
		return ticksPerSecond;
	}
	protected int framesPerSecond()
	{
		return framesPerSecond;
	}
	protected int tickMillisOffset()
	{
		return tickMillisOffset;
	}
	protected int frameMillisOffset()
	{
		return frameMillisOffset;
	}
	
	public long deltaFrame()
	{
		return System.currentTimeMillis() - lastFrameMillis;
	}
	public long deltaTick()
	{
		return System.currentTimeMillis() - lastTickMillis;
	}
	
	public long frameCount()
	{
		return frameCount;
	}
	public long tickCount()
	{
		return tickCount;
	}
	
	
	public void enableConsoleFPSCounter()
	{
		consoleFPSCounter = true;
	}
	public void disableConsoleFPSCounter()
	{
		consoleFPSCounter = false;
	}
	
	
	
	
	public static void main(String[] args)
	{
		
	}
}
