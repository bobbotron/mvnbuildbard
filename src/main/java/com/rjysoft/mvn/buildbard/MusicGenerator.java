package com.rjysoft.mvn.buildbard;

import org.apache.maven.execution.ExecutionEvent;
import org.apache.maven.execution.MavenExecutionResult;
import org.jfugue.pattern.Pattern;
import org.jfugue.theory.ChordProgression;

public class MusicGenerator
{

	private static final String MAVEN_COMPILER_PLUGIN_MOJO = "maven-compiler-plugin";
	
	private static final Pattern START_MOJO = null;
	private static final Pattern MOJO_SUCCESS = null;
	private static final Pattern MVN_COMPLETE = new Pattern("T160 I[Synth_Drum] Emajq Gmajq Amajq Emajq Gmajq Amajq Rh");
	private static final Pattern COMPILE_MOJO_START = new Pattern("I[Woodblock] a3i");
	private static final Pattern COMPILE_MOJO_SUCCESS = new Pattern("I[Woodblock] e3i");
	
	public Pattern generatePattern(Object event)
	{
		if (event == null)
		{
			return null;
		}
		
		if (event instanceof ExecutionEvent)
		{
			ExecutionEvent ee = (ExecutionEvent) event;
			return generateExecutionEventPattern(ee);
		}
		else if (event instanceof MavenExecutionResult)
		{
			return MVN_COMPLETE;
		}
		return null;
	}
	
	private Pattern generateExecutionEventPattern(ExecutionEvent ee)
	{
		if (ee == null || ee.getType() == null)
		{
			return null;
		}
		
		final ExecutionEvent.Type eeType = ee.getType();

		switch (eeType)
		{
			case ProjectSucceeded:
					return null;
			case ProjectFailed:
					Pattern p = new ChordProgression("I")
						.distribute("7%6")
						.allChordsAs("$0")
						.eachChordAs("$0ia100 $0ia100")
						.getPattern()
						.setInstrument("SCI_FI")
						.setTempo(120);
					return p;
			case MojoStarted:
				if (ee.getMojoExecution().getArtifactId().equals(MAVEN_COMPILER_PLUGIN_MOJO))
				{
					return COMPILE_MOJO_START;
				}
				return START_MOJO;
			case MojoSucceeded:
				if (ee.getMojoExecution().getArtifactId().equals(MAVEN_COMPILER_PLUGIN_MOJO))
				{
					return COMPILE_MOJO_SUCCESS;
				}
				return MOJO_SUCCESS;
			default:
				return null;
		}
	}
}
