package de.ricepuffz.rice2d.resource;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBVorbis;

public class SoundLoader
{
	private SoundLoader() { }
	
	public static SoundData loadSound(String path)
	{
		IntBuffer channelsBuffer = BufferUtils.createIntBuffer(1);
		IntBuffer sampleRateBuffer = BufferUtils.createIntBuffer(1);
		
		ShortBuffer rawAudioBuffer  = STBVorbis.stb_vorbis_decode_filename(path, channelsBuffer, sampleRateBuffer);
		
		if (rawAudioBuffer == null)
			throw new IllegalStateException("SoundLoader ERROR: Couldn't load sound '" + path + "'!");
		
		return new SoundData(channelsBuffer, sampleRateBuffer, rawAudioBuffer);
	}
}
