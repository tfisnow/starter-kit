package com.generic.validateorders;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.generic.exceptions.WaitException;
import com.generic.test.base.TestBase;
import com.generic.utilities.Logg;
import com.web.pages.GoogleSearchPage;
import com.web.pages.GoogleSearchResultsPage;

/**
 * This is a template page only for testing the code. You may remove this if not required.
 *
 */
public class WebAppTest extends TestBase {

	private GoogleSearchPage googleSearch; 
	private GoogleSearchResultsPage searchResults;
	private WebDriver driver ;


	@BeforeClass
	public void beforeClass(ITestContext context) throws WaitException {
		driver = getWebDriverInstance(context);
	}

	@Test
	public void verifyResultsOnGoogleSearchPage() throws Exception {
    	googleSearch = new GoogleSearchPage(driver);

		Assert.assertTrue("Search page not loaded !!", googleSearch.goToSearchPage());
		searchResults = googleSearch.enterSearchText();
		String searchText = searchResults.getSearchResult();
		Assert.assertTrue("Search Results don't match",
				searchText.contains("CitiusTech") ? true : false);
	}	
}
