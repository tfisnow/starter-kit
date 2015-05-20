package com.generic;

import java.util.List;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.web.selenium.SeleniumConfiguration;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;
import static org.jbehave.web.selenium.WebDriverHtmlOutput.WEB_DRIVER_HTML;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.failures.PendingStepStrategy;
import org.jbehave.core.failures.FailingUponPendingStep;

import com.generic.validateorders.WebAppTest;

public class StoryRunner extends JUnitStories {
	
    private PendingStepStrategy pendingStepStrategy = new FailingUponPendingStep();
	
	private CrossReference crossReference = new CrossReference().withJsonOnly()
            .withPendingStepStrategy(pendingStepStrategy)
            .withOutputAfterEachStory(true)
            .excludingStoriesWithNoExecutedScenarios(true);

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        return new SeleniumConfiguration()
            .useStoryLoader(new LoadFromClasspath(embeddableClass))
            .useStoryReporterBuilder(reporterBuilder
                .withCodeLocation(codeLocationFromClass(embeddableClass))
                .withDefaultFormats()
                .withFormats(CONSOLE, TXT, HTML, XML));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(),
                new WebAppTest());
    }
   
    /*This will pick all the feature files by default, 
     * if required to run only a specific one then will have to mention instead of '*'     
     * */
    @Override
    protected List<String> storyPaths() {
        return new StoryFinder()
                .findPaths(codeLocationFromClass(this.getClass()).getFile(), asList("**/GoToGoogle.feature"), null);
    }
    
    private Format[] formats = new Format[]{
            CONSOLE,  HTML, TXT,
           XML, WEB_DRIVER_HTML};
    
    private StoryReporterBuilder reporterBuilder = new StoryReporterBuilder()
    .withCodeLocation(codeLocationFromClass(StoryRunner.class))
    .withReporters(new com.generic.utilities.JbehaveReporter()).withFailureTrace(true)
    .withFailureTraceCompression(true).withDefaultFormats()
    .withFormats(formats).withCrossReference(crossReference);

}