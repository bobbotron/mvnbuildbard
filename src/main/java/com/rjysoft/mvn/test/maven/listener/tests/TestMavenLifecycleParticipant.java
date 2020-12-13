package com.rjysoft.mvn.test.maven.listener.tests;

import org.apache.maven.*;
import org.apache.maven.execution.MavenSession;
import org.codehaus.plexus.component.annotations.Component;

// @Component(role = AbstractMavenLifecycleParticipant.class, hint = "testLifecycle")
public class TestMavenLifecycleParticipant extends AbstractMavenLifecycleParticipant
{

	@Override
	public void afterSessionStart(MavenSession session)
		throws MavenExecutionException
	{
		System.out.println("afterSessionStart");

	}

	@Override
	public void afterProjectsRead(MavenSession session)
		throws MavenExecutionException
	{
		System.out.println("afterProjectsRead");
	}

	@Override
	public void afterSessionEnd(MavenSession session) throws MavenExecutionException
	{
		System.out.println("afterProjectsEnd");
		try
		{
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
		}
	}

}
