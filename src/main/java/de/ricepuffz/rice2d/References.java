package de.ricepuffz.rice2d;

import de.ricepuffz.rice2d.audio.SoundManager;
import de.ricepuffz.rice2d.input.InputManager;
import de.ricepuffz.rice2d.rendering.Camera;
import de.ricepuffz.rice2d.rendering.Window;
import de.ricepuffz.rice2d.resource.ResourceManager;
import de.ricepuffz.rice2d.scene.SceneManager;

public class References
{
	//Change this value to 'true' only when not using Rice2D as a maven dependency or a jar in your build path!!
	public static boolean usingSourceDirectly = false;
	
	public static SceneManager sceneManager = null;
	public static ResourceManager resourceManager = null;
	public static InputManager inputManager = null;
	public static SoundManager audioManager = null;
	public static ThreadCollection threadCollection = null;
	
	public static Window window = null;
	public static Camera camera = null;
}
