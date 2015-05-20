package com.generic.validateorders; 

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.generic.test.base.TestBase;
import com.generic.utilities.Logg;
import com.web.pages.GoogleSearchPage;
import com.web.pages.GoogleSearchResultsPage;

/**
 * This is a template page only for testing the code. You may remove this if not required.
 *
 */
public class WebAppTest extends TestBase  {

    private static final Logger LOGGER = Logg.createLogger();

	private GoogleSearchPage googleSearch; 
	GoogleSearchResultsPage searchResults;
		
    
	@Test
    public void verifySearchResultsOnGoogle() throws Exception {
		LOGGER.debug("Test Method");
		googleSearch = new GoogleSearchPage(webdriver);

    	Assert.assertTrue("Search page not loaded !!", googleSearch.goToSearchPage());
    	
    	searchResults = googleSearch.enterSearchText();
    	
		String searchText = searchResults.getSearchResult();
		Assert.assertTrue("Search Results don't match",
				searchText.contains("CitiusTech") ? true : false);
	}	
}
