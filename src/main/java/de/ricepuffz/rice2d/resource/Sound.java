package de.ricepuffz.rice2d.resource;

import org.lwjgl.openal.AL10;

public class Sound
{
	private int bufferPointer;
	
	public Sound(String path)
	{
		SoundData soundData = SoundLoader.loadSound("./assets/sounds/" + path);
		
		int format = -1;
		
		int channels = soundData.channelsBuffer().get();
		if (channels == 1)
			format = AL10.AL_FORMAT_MONO16;
		else if (channels == 2)
			format = AL10.AL_FORMAT_STEREO16;
		
		bufferPointer = AL10.alGenBuffers();
		AL10.alBufferData(bufferPointer, format, soundData.rawAudioBuffer(), soundData.sampleRateBuffer().get());
	}
	
	public int bufferPointer() { return bufferPointer; }
}
