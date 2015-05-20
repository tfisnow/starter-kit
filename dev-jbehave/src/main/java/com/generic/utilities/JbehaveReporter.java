package com.generic.utilities;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.model.GivenStories;
import org.jbehave.core.model.Lifecycle;
//import org.jbehave.core.model.Lifecycle;
import org.jbehave.core.model.Meta;
import org.jbehave.core.model.Narrative;
import org.jbehave.core.model.OutcomesTable;
import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.jbehave.core.model.StoryDuration;
import org.jbehave.core.reporters.StoryReporter;
import org.openqa.selenium.WebDriver;

import com.generic.actions.WebActions;
import com.generic.exceptions.WaitException;
import com.generic.page.base.WebPageBase;

public class JbehaveReporter implements StoryReporter{

    protected static final Logger LOGGER = Logg.createLogger();

	private String scenarioName;

	public void storyNotAllowed(Story story, String filter) {
		// TODO Auto-generated method stub
		
	}

	public void storyCancelled(Story story, StoryDuration storyDuration) {
		// TODO Auto-generated method stub
		
	}

	public void beforeStory(Story story, boolean givenStory) {
		// TODO Auto-generated method stub
		
	}

	public void afterStory(boolean givenStory) {
		// TODO Auto-generated method stub
		
	}

	public void narrative(Narrative narrative) {
		// TODO Auto-generated method stub
		
	}

	/*public void lifecyle(Lifecycle lifecycle) {
		// TODO Auto-generated method stub
		
	}*/

	public void scenarioNotAllowed(Scenario scenario, String filter) {
		// TODO Auto-generated method stub
		
	}

	public void beforeScenario(String scenarioTitle) {
		scenarioName=scenarioTitle;
		
	}

	public void scenarioMeta(Meta meta) {
		// TODO Auto-generated method stub
	}

	public void afterScenario() {
		// TODO Auto-generated method stub
	}

	public void givenStories(GivenStories givenStories) {
		// TODO Auto-generated method stub
	}

	public void givenStories(List<String> storyPaths) {
		// TODO Auto-generated method stub
		
	}

	public void beforeExamples(List<String> steps, ExamplesTable table) {
		// TODO Auto-generated method stub
	}

	public void example(Map<String, String> tableRow) {
		// TODO Auto-generated method stub
	}

	public void afterExamples() {
		// TODO Auto-generated method stub
		
	}

	public void beforeStep(String step) {
		// TODO Auto-generated method stub
		
	}

	public void successful(String step) {
		// TODO Auto-generated method stub
	}

	public void ignorable(String step) {
		// TODO Auto-generated method stub
	}

	public void pending(String step) {
		// TODO Auto-generated method stub
	}

	public void notPerformed(String step) {
		// TODO Auto-generated method stub
	}

	public void failed(String step, Throwable cause) {
		LOGGER.info("Scenario Name : "+scenarioName);
		WebActions.takeScreenShot(scenarioName);		
	}

	public void failedOutcomes(String step, OutcomesTable table) {
		// TODO Auto-generated method stub
		
	}

	public void restarted(String step, Throwable cause) {
		// TODO Auto-generated method stub
	}

	public void dryRun() {
		// TODO Auto-generated method stub
	}

	public void pendingMethods(List<String> methods) {
		// TODO Auto-generated method stub	
	}

	public void lifecyle(Lifecycle lifecycle) {
		// TODO Auto-generated method stub
		
	}
}
