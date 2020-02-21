package de.ricepuffz.rice2d.resource;

import java.util.HashMap;
import java.util.Map;

import de.ricepuffz.rice2d.References;
import de.ricepuffz.rice2d.resource.model.Model;
import de.ricepuffz.rice2d.resource.model.TexturedQuad;

public class ResourceManager
{
	private Map<String, Texture> textures = null;
	private Map<String, Shader> shaders = null;
	private Map<String, Model> models = null;
	private Map<String, Sound> sounds = null;
	
	public ResourceManager()
	{
		textures = new HashMap<String, Texture>();
		shaders = new HashMap<String, Shader>();
		models = new HashMap<String, Model>();
		sounds = new HashMap<String, Sound>();
		
		registerTexture(Texture.EMPTY, "empty");
		registerShader(new Shader("default", References.usingSourceDirectly ? false : true), "default");
		registerModel(new TexturedQuad(), "default");
	}
	
	
	public void registerTexture(Texture texture, String name)
	{
		if (textures.containsKey(name))
			System.err.println("ResourceManager ERROR: Failed to load texture '" + name + "' because a texture with that name already exists!");
		else
			textures.put(name, texture);
	}
	
	public void registerShader(Shader shader, String name)
	{
		if (shaders.containsKey(name))
			System.err.println("ResourceManager ERROR: Failed to register shader '" + name + "' because a shader with that name already exists!");
		else
			shaders.put(name, shader);
	}
	
	public void registerModel(Model model, String name)
	{
		if (models.containsKey(name))
			System.err.println("ResourceManager ERROR: Failed to register model '" + name + "' because a model with that name already exists!");
		else
			models.put(name, model);
	}
	
	public void registerSound(Sound sound, String name)
	{
		if (sounds.containsKey(name))
			System.err.println("ResourceManager ERROR: Failed to register sound '" + name + "' because a sound with that name already exists!");
		else
			sounds.put(name, sound);
	}
	
	
	public Texture getTexture(String name)
	{
		Texture texture = textures.get(name);
		if (texture != null)
			return textures.get(name);
		else
		{
			System.err.println("ResourceManager ERROR: Tried to use non-existant texture '" + name + "'!");
			return null;
		}
	}
	
	public Shader getShader(String name)
	{
		Shader shader = shaders.get(name);
		if (shader != null)
			return shaders.get(name);
		else
		{
			System.err.println("ResourceManager ERROR: Tried to use non-existant shader '" + name + "'!");
			return null;
		}
	}
	
	public Model getModel(String name)
	{
		Model model = models.get(name);
		if (model != null)
			return models.get(name);
		else
		{
			System.err.println("ResourceManager ERROR: Tried to use non-existant model '" + name + "'!");
			return null;
		}
	}
	
	public Sound getSound(String name)
	{
		Sound sound = sounds.get(name);
		if (sound != null)
			return sounds.get(name);
		else
		{
			System.err.println("ResourceManager ERROR: Tried to use non-existant sound '" + name + "'!");
			return null;
		}
	}
	
	public boolean hasTexture(String name)
	{
		return textures.get(name) != null;
	}
	
	public void removeModel(String name)
	{
		models.remove(name);
	}
}
