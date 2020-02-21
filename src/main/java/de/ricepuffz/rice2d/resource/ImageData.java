package de.ricepuffz.rice2d.resource;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ImageData
{
	private IntBuffer width = null;
	private IntBuffer height = null;
	private IntBuffer comp = null;
	private ByteBuffer data = null;
	
	ImageData(IntBuffer width, IntBuffer height, IntBuffer comp, ByteBuffer data)
	{
		this.width = width;
		this.height = height;
		this.comp = comp;
		this.data = data;
	}
	
	public IntBuffer width() { return width; }
	public IntBuffer height() { return height; }
	public IntBuffer comp() { return comp; }
	public ByteBuffer data() { return data; }
}
