package de.ricepuffz.rice2d.resource.model;

public class TexturedQuad extends Model
{
	public static final float[] VERTICES = new float[] {
			-0.5f, 0.5f, 0,	//TOP LEFT		0
			0.5f, 0.5f, 0,	//TOP RIGHT		1
			0.5f, -0.5f, 0,	//BOTTOM RIGHT	2
			-0.5f, -0.5f, 0	//BOTTOM LEFT	3
	};
	
	public static final float[] TEX_COORDS = new float[] {
			0, 0,
			1, 0,
			1, 1,
			0, 1
	};
	
	public static final int[] INDICES = new int[] {
			0, 1, 2,
			2, 3, 0
	};
	
	public TexturedQuad()
	{
		super(VERTICES, TEX_COORDS, INDICES);
	}
}
