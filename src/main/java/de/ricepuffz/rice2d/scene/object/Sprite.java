package de.ricepuffz.rice2d.scene.object;

import java.util.Random;

import de.ricepuffz.rice2d.References;
import de.ricepuffz.rice2d.resource.ResourceManager;
import de.ricepuffz.rice2d.resource.Texture;
import de.ricepuffz.rice2d.resource.model.Model;
import de.ricepuffz.rice2d.resource.model.TexturedQuad;
import de.ricepuffz.rice2d.scene.SceneObject;

public class Sprite extends SceneObject
{
	private static final Random RANDOM = new Random();
	
	private String usedModel = "default";
	
	public Sprite()
	{
		super("empty", "default", "default");
	}
	
	public Sprite(String texture)
	{
		super(texture, modelName(texture), "default");
	}
	
	public static String modelName(String textureName)
	{
		ResourceManager resourceManager = References.resourceManager;
		
		Texture texture = References.resourceManager.getTexture(textureName);
		
		if (texture.getHeight() == texture.getWidth())
			return "default";
		else {
			float[] vertices = null;
			
			float textureWidth = texture.getWidth();
			float textureHeight = texture.getHeight();
			
			if (textureWidth < textureHeight)
			{
				float newUpDownValue = textureHeight / textureWidth / 2;
				vertices = new float[] {
						-0.5f, newUpDownValue, 0,	//TOP LEFT		0
						0.5f, newUpDownValue, 0,	//TOP RIGHT		1
						0.5f, -newUpDownValue, 0,	//BOTTOM RIGHT	2
						-0.5f, -newUpDownValue, 0	//BOTTOM LEFT	3
				};
			} else {
				float newLeftRightValue = textureWidth / textureHeight / 2;
				vertices = new float[] {
						-newLeftRightValue, 0.5F, 0,	//TOP LEFT		0
						newLeftRightValue, 0.5F, 0,		//TOP RIGHT		1
						newLeftRightValue, -0.5F, 0,	//BOTTOM RIGHT	2
						-newLeftRightValue, -0.5F, 0	//BOTTOM LEFT	3
				};
			}
			
			Model model = new Model(vertices, TexturedQuad.TEX_COORDS, TexturedQuad.INDICES);
			
			String randomName = String.valueOf(RANDOM.nextInt(Integer.MAX_VALUE-1000) + 1000);
			while (resourceManager.hasTexture(randomName))
				randomName = String.valueOf(RANDOM.nextInt(Integer.MAX_VALUE-1000) + 1000);
			
			References.resourceManager.registerModel(model, randomName);
			
			return randomName;
		}
	}
	
	public String usedModel() { return usedModel.equals("default") ? null : usedModel; }
}
