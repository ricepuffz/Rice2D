package de.ricepuffz.rice2d.resource;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

public class ImageLoader
{
	private ImageLoader() {}
	
	public static ImageData loadImage(String path)
	{
		IntBuffer width = BufferUtils.createIntBuffer(1);
		IntBuffer height = BufferUtils.createIntBuffer(1);
		IntBuffer comp = BufferUtils.createIntBuffer(1);
		
		ByteBuffer data = STBImage.stbi_load(path, width, height, comp, 4);
		
		if (data == null)
			throw new IllegalStateException("ImageLoader ERROR: Couldn't load image '" + path + "'!");
		
		return new ImageData(width, height, comp, data);
	}
	
	public static ImageData loadImageNoStbi(InputStream stream)
	{
		IntBuffer width = BufferUtils.createIntBuffer(1);
		IntBuffer height = BufferUtils.createIntBuffer(1);
		IntBuffer comp = BufferUtils.createIntBuffer(1);
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ByteBuffer data = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			baos.flush();
			byte[] imageBytes = baos.toByteArray();
			baos.close();
			//data = ByteBuffer.wrap(imageBytes);
			data = ByteBuffer.allocateDirect(imageBytes.length);
			for (byte b : imageBytes)
				data.put(b);
			data.flip();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		width.put(image.getWidth());
		width.flip();
		
		height.put(image.getHeight());
		height.flip();
		
		comp.put(4);
		comp.flip();
		
		return new ImageData(width, height, comp, data);
	}
}
