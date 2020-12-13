package com.rjysoft.mvn.test.maven.listener;

import org.apache.maven.eventspy.EventSpy;
import org.apache.maven.execution.ExecutionEvent;
import org.apache.maven.execution.MavenExecutionResult;
import org.codehaus.plexus.component.annotations.Component;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.theory.ChordProgression;

/**
 * This maven event spy listens for key maven events and links them to JFugue
 * notes
 */
@Component(role = EventSpy.class, hint = "mvnbuildbardspy")
public class MidiPlayerSpy extends org.apache.maven.eventspy.AbstractEventSpy
{

	private final BardPlayer player = new BardPlayer();
	private final MusicGenerator generator = new MusicGenerator();

	@Override
	public void onEvent(Object event) throws Exception
	{
		if (event == null)
		{
			return;
		}
	
		player.play(generator.generatePattern(event));
		
		if (event instanceof MavenExecutionResult)
		{
			Thread.sleep(3000);
		}
	}
}
