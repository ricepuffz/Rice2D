package de.ricepuffz.rice2d.scene;

import java.util.HashMap;
import java.util.Map;

public class SceneManager
{
	private Scene currentScene = null;
	
	private Map<String, Scene> scenes = null;
	
	public SceneManager()
	{
		scenes = new HashMap<String, Scene>();
	}
	
	
	public Scene getCurrentScene() { return currentScene; }
	
	public void registerScene(Scene scene)
	{
		if (scenes.containsKey(scene.getName()) || scenes.containsValue(scene))
			System.err.println("SceneManager ERROR: Couldn't register scene '" + scene.getName() + "' because the scene is already being used or a scene with the same name already exists!");
		else
			scenes.put(scene.getName(), scene);
	}
	
	public void loadScene(String scene)
	{
		if (!scenes.containsKey(scene))
			System.err.println("SceneManager ERROR: Couldn't load scene '" + scene + "' because it doesn't exist!");
		else
			currentScene = scenes.get(scene);
	}
}
