package de.ricepuffz.rice2d.resource;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import de.ricepuffz.rice2d.References;

public class Texture
{
	private int id;
	private int width;
	private int height;
	
	public static Texture EMPTY = new Texture("empty.png", References.usingSourceDirectly ? false : true);
	
	public Texture(String path)
	{
		this(path, false);
	}
	
	public Texture(String path, boolean systemTexture)
	{
		if (!systemTexture)
			loadTexture("./assets/textures/" + path);
		else {
			ImageData data = ImageLoader.loadImageNoStbi(this.getClass().getClassLoader().getResourceAsStream("assets/textures/" + path));
			loadTexture(data);
		}
	}
	
	
	private void loadTexture(String path)
	{
		ImageData textureData = ImageLoader.loadImage(path);
		loadTexture(textureData);
	}
	
	private void loadTexture(ImageData textureData)
	{
		id = GL11.glGenTextures();
		this.width = textureData.width().get();
		this.height = textureData.height().get();
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
		
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, this.width, this.height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, textureData.data());
	}
	
	public void bind(int sampler)
	{
		if (sampler >= 0 && sampler <= 31) {
			GL13.glActiveTexture(GL13.GL_TEXTURE0 + sampler);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
		}
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
}
