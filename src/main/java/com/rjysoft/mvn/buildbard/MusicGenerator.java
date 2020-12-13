package com.rjysoft.mvn.buildbard;

import org.apache.maven.execution.ExecutionEvent;
import org.apache.maven.execution.MavenExecutionResult;
import org.eclipse.aether.RepositoryEvent;
import org.jfugue.pattern.Pattern;
import org.jfugue.theory.ChordProgression;

public class MusicGenerator
{

	private static final String MAVEN_COMPILER_PLUGIN_MOJO = "maven-compiler-plugin";
	
	private static final Pattern START_MOJO = null; // compilePattern("V1 I[Slap_Bass_1] a2i");
	private static final Pattern MOJO_SUCCESS = compilePattern("V1 I[Slap_Bass_1] e2i");
	private static final Pattern MVN_COMPLETE = compilePattern("V3 I[Synth_Drum] Emajq Gmajq Amajq Emajq Gmajq Amajq Rh");
	private static final Pattern COMPILE_MOJO_START = compilePattern("V1 I[Slap_Bass_1] a3i");
	private static final Pattern COMPILE_MOJO_SUCCESS = compilePattern("V1 I[Slap_Bass_1] e3i");
	private static final Pattern PROJECT_FAILED_PATTERN = new ChordProgression("I")
		.distribute("7%6")
		.allChordsAs("$0")
		.eachChordAs("$0ia100 $0ia100")
		.getPattern()
		.setInstrument("SCI_FI")
		.setTempo(120);
	private static final Pattern STARTUP = compilePattern("V2 I[Slap_Bass_1] ai ri bi ri ci di aq bq rw");
	private static final Pattern ARTIFACT_RESOLVING = null; // compilePattern("V2 I[Slap_Bass_1] e2s");
	private static final Pattern ARTIFACT_RESOLVED = null; // compilePattern("V2 I[Slap_Bass_1] a2s");
	
	public MusicGenerator()
	{
	}
	
	private static Pattern compilePattern(String pattern)
	{
		return new Pattern(pattern).atomize();
	}
	
	public Pattern generateStartupNoise()
	{
		return STARTUP;
	}
	
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
		else if (event instanceof RepositoryEvent)
		{
			return generateRepositoryEvent((RepositoryEvent)event);
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
					return PROJECT_FAILED_PATTERN;
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

	private Pattern generateRepositoryEvent(RepositoryEvent repositoryEvent)
	{
		if  (repositoryEvent.getType().equals(RepositoryEvent.EventType.ARTIFACT_RESOLVED))
		{
			return ARTIFACT_RESOLVED;
		}
		else if (repositoryEvent.getType().equals(RepositoryEvent.EventType.ARTIFACT_RESOLVING))
		{
			return ARTIFACT_RESOLVING;
		}
		return null;
	}
}
