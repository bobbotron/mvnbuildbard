package com.rjysoft.mvn.test.maven.listener;

import javax.sound.midi.MidiUnavailableException;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.realtime.RealtimePlayer;

public class BardPlayer
{

	private RealtimePlayer player;

	public BardPlayer()
	{
		try
		{
			player = new RealtimePlayer();
			
			// Play a short rest note to get things initialized
			player.play("Rq");
		}
		catch (MidiUnavailableException ex)
		{
			ex.printStackTrace();
			player = null;
		}
	}

	public void play(Pattern p)
	{
		if (p != null && player != null)
		{
			player.play(p);
		}
	}
}
