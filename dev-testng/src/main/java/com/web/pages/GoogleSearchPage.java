package com.web.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.generic.exceptions.URLNavigationException;
import com.generic.exceptions.WaitException;
import com.generic.page.base.WebPageBase;
import com.generic.utilities.Logg;
import com.web.locators.GoogleSearchLocators;

public class GoogleSearchPage extends WebPageBase implements GoogleSearchLocators{

    private static final Logger LOGGER = Logg.createLogger();

	public GoogleSearchPage(WebDriver driver) throws WaitException {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean goToSearchPage() throws URLNavigationException{

		webActions.navigateToBaseURL();

		try {
			return webActions.getVisibiltyOfElementLocatedBy(SEARCH_BOX);
		} catch (WaitException e) {
			LOGGER.error(e.getStackTrace());		
			return false;
		}
	}

	public GoogleSearchResultsPage enterSearchText() throws URLNavigationException{

		try {
			webActions.enterText(VISIBILITY, SEARCH_BOX, "CitiusTech");
			webActions.hitEnter(SEARCH_BOX);
			return new GoogleSearchResultsPage(driver);
		} catch (TimeoutException e) {
			LOGGER.error(e.getStackTrace());
			
		} catch (WaitException e) {
			LOGGER.error(e.getStackTrace());
		}
		return null;		
	}
}
