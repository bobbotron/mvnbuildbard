package com.rjysoft.mvn.test.maven.listener.tests;

import org.apache.maven.execution.ExecutionEvent;
import org.apache.maven.execution.ExecutionListener;
import org.codehaus.plexus.component.annotations.Component;

// @Component(role = ExecutionListener.class, hint = "execListener")
public class TestExecutionListener extends org.apache.maven.execution.AbstractExecutionListener    
{

    @Override
    public void projectSucceeded(ExecutionEvent event)
    {
        System.out.println("TEL Success! " + event);
    }

	@Override
	public void sessionEnded(ExecutionEvent event)
	{
		System.out.println("TEL Ended! " + event);
	}
    
}
