package com.rjysoft.mvn.test.maven.listener.tests;

import org.apache.maven.eventspy.EventSpy;
import org.apache.maven.execution.ExecutionEvent;
import org.apache.maven.execution.MavenExecutionResult;
import org.codehaus.plexus.component.annotations.Component;
import org.eclipse.aether.RepositoryEvent;

//@Component(role = EventSpy.class, hint = "testEventSpy")
public class TestEventSpy extends org.apache.maven.eventspy.AbstractEventSpy
{
    @Override
    public void init(Context context) throws Exception
    {
        System.out.println("TestListener init");
    }

    @Override
    public void onEvent(Object event) throws Exception
    {
		if (event == null)
		{
			return;
		}
		
        if (event instanceof ExecutionEvent)
        {
            ExecutionEvent ee = (ExecutionEvent) event;
            System.out.println("Execution event is: " + ee.toString() + " type " + ee.getType() + ", mojo " + ee.getMojoExecution().getArtifactId());
            
        }
        else if (event instanceof MavenExecutionResult)
        {
            MavenExecutionResult mer = (MavenExecutionResult) event;
            System.out.println("Mer " + mer.getBuildSummary(mer.getProject()));
        }
        else if (event instanceof RepositoryEvent)
        {
            // do nothing, this generates a ton of logging like this: 
            /**
             * Event: class org.eclipse.aether.RepositoryEvent
             * ARTIFACT_INSTALLED
             * com.rjysoft.mvn:test-maven-listener:pom:1.0-SNAPSHOT
             * (~\mvn\test-maven-listener\1.0-SNAPSHOT\test-maven-listener-1.0-SNAPSHOT.pom)
             * @ ~\.m2\repository (enhanced) Event: class
             * org.eclipse.aether.RepositoryEvent METADATA_INSTALLING
             * com.rjysoft.mvn:test-maven-listener:1.0-SNAPSHOT/maven-metadata.xml
             * (~\mvn\test-maven-listener\1.0-SNAPSHOT\maven-metadata-local.xml)
             * @ ~\.m2\repository (enhanced)
             *
             */
			RepositoryEvent re = (RepositoryEvent) event;
            System.out.println("Repo Event: " + re );
        }
        else
        {
            System.out.println("Event: " + event.getClass() + " " + event.toString());
        }

    }

}
