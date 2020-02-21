package de.ricepuffz.rice2d.resource;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public class SoundData
{
	private IntBuffer channelsBuffer = null;
	private IntBuffer sampleRateBuffer = null;
	private ShortBuffer rawAudioBuffer = null;
	
	public SoundData(IntBuffer channelsBuffer, IntBuffer sampleRateBuffer, ShortBuffer rawAudioBuffer)
	{
		this.channelsBuffer = channelsBuffer;
		this.sampleRateBuffer = sampleRateBuffer;
		this.rawAudioBuffer = rawAudioBuffer;
	}
	
	public IntBuffer channelsBuffer() { return channelsBuffer; }
	public IntBuffer sampleRateBuffer() { return sampleRateBuffer; }
	public ShortBuffer rawAudioBuffer() { return rawAudioBuffer; }
}
