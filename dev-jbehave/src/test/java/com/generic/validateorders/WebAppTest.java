package com.generic.validateorders; 
import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.generic.page.base.WebPageBase;
import com.generic.test.base.TestBase;
import com.generic.utilities.Logg;
import com.web.pages.GoogleSearchResultsPage;
import com.web.pages.GoogleSearchPage;
import com.web.pages.HomePage;

/**
 * This is a template page only for testing the code. You may remove this if not required.
 *
 */
public class WebAppTest extends TestBase  {

	private GoogleSearchPage googleSearch; 
	GoogleSearchResultsPage searchResults;

	@SuppressWarnings("deprecation")
	@Given("User is on the Google search page")
    public void verifyUserOnSearchPage() throws Exception {
    	googleSearch = new GoogleSearchPage(webdriver);

    	Assert.assertTrue("Search page not loaded !!", googleSearch.goToSearchPage());
    	LOGG.info("In the Given method");
		
	}   
    
    @When("User enters a text on the search box")
    public void enterTextInSearchBox() throws Exception {
    	searchResults = googleSearch.enterSearchText();
    	LOGG.info("In the When method");
    	
	}
    
    @SuppressWarnings("deprecation")
    @Then("User is able to get the desired results on the search page")
    public void searchResultsTest() throws Exception {
    	String searchText = searchResults.getSearchResult();
    	Assert.assertTrue("Search Results don't match",
    			searchText.contains("CitiusTech") ? true : false);
    }
}