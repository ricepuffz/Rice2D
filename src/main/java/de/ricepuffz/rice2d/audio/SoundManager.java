package de.ricepuffz.rice2d.audio;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;

import de.ricepuffz.rice2d.resource.Sound;

public class SoundManager
{
	private String defaultDeviceName = null;
	private long device = -1;
	private long context = -1;
	
	private ALCCapabilities alcCapabilities = null;
	private ALCapabilities alCapabilities = null;
	
	private boolean capableOfAL10 = false;
	
	public SoundManager()
	{
		defaultDeviceName = ALC10.alcGetString(0, ALC10.ALC_DEFAULT_DEVICE_SPECIFIER);
		device = ALC10.alcOpenDevice(defaultDeviceName);
		
		int[] attributes = new int[] { 0 };
		context = ALC10.alcCreateContext(device, attributes);
		ALC10.alcMakeContextCurrent(context);
		
		alcCapabilities = ALC.createCapabilities(device);
		alCapabilities = AL.createCapabilities(alcCapabilities);
		
		capableOfAL10 = alCapabilities.OpenAL10;
	}
	
	public void destroyAudioManager()
	{
		ALC10.alcDestroyContext(context);
		ALC10.alcCloseDevice(device);
	}
	
	
	
	public void playGlobally(Sound sound)
	{
		if (!capableOfAL10)
			return;
		
		int sourcePointer = AL10.alGenSources();
		AL10.alSourcei(sourcePointer, AL10.AL_BUFFER, sound.bufferPointer());
		AL10.alSourcePlay(sourcePointer);
		//AL10.alDeleteSources(sourcePointer);
	}
}
