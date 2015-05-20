package com.generic.actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.io.FileUtils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.generic.comparator.Comparator;
import com.generic.exceptions.URLNavigationException;
import com.generic.exceptions.WaitException;
import com.generic.property.PropertyManager;
import com.generic.utilities.DateAndTime;
import com.generic.utilities.Logg;
import com.generic.utilities.Utilities;
import com.generic.waits.WebDriverWaits;

public class WebActions implements IWebAction {

    private WebDriver driver;
    private static WebDriver WEB_DRIVER = null ;
    protected final Comparator compare = new Comparator();
    private static final Logger LOGGER = Logg.createLogger();
    protected final WebDriverWaits wait = new WebDriverWaits();
    private static final Properties APPLICATIONPROPERTY = PropertyManager.loadApplicationPropertyFile();
    private static final String APPLICATIONURL = "applicationURL";
    
    public WebActions(WebDriver driver) {
        WEB_DRIVER = driver;

    	this.driver = driver;
    }

    public void setCookie(String key, String value) {
    	Cookie cookie = new Cookie(key, value);
        LOGGER.info(Utilities.getCurrentThreadId() + "Cookie description");
        LOGGER.info(Utilities.getCurrentThreadId() + "Key=" + key + " " + "Value=" + value);
        driver.manage().addCookie(cookie);
        LOGGER.info(Utilities.getCurrentThreadId() + "Successfully added cookie named " + key
                + " to the HTML page");
    }

    public String getCookie(String key) {
        LOGGER.info(Utilities.getCurrentThreadId() + "Retrieving the value "
                + driver.manage().getCookieNamed(key).getValue() + " stored in the cookie");
        return driver.manage().getCookieNamed(key).getValue();
    }

    public void navigateToURL(String url) throws URLNavigationException {
            LOGGER.info(Utilities.getCurrentThreadId()
                    + "Navigating to Application URL on Local Browser:"
                    + APPLICATIONPROPERTY.getProperty(APPLICATIONURL));
            driver.get(APPLICATIONPROPERTY.getProperty(APPLICATIONURL));
            LOGGER.info(Utilities.getCurrentThreadId()
                    + "Successfully navigated to Application URL on the Local Browser");
    }
    
    public void navigateToBaseURL() throws URLNavigationException {
        LOGGER.info(Utilities.getCurrentThreadId()
                + "Navigating to Application URL on Local Browser:"
                + APPLICATIONPROPERTY.getProperty(APPLICATIONURL));
        driver.get(APPLICATIONPROPERTY.getProperty(APPLICATIONURL));
       
               LOGGER.info(Utilities.getCurrentThreadId()
                + "Successfully navigated to Application URL on the Local Browser");
}

    public void closeBrowser() {
    	driver.close();
    	driver.quit();
        LOGGER.info(Utilities.getCurrentThreadId() + "Closing the browser");
        LOGGER.info(Utilities.getCurrentThreadId() + "Sucessfully closed the browser" + "\n");
    }

    public void enterText(String syncKey, By element, String value) throws TimeoutException,
            WaitException {
        WebElement webElement = null;
        webElement = wait.syncElementUsing(syncKey, driver, element);
        LOGGER.info(Utilities.getCurrentThreadId() + "Clearing the content of the text box");
        webElement.clear();
        LOGGER.info(Utilities.getCurrentThreadId() + "Contents cleared");
        webElement.sendKeys(value);
        LOGGER.info(Utilities.getCurrentThreadId() + "Entered text:" + value
                + " in text box with locator:" + element);
    }

    public void click(String syncKey, By element) throws TimeoutException, WaitException {
        wait.syncElementUsing(syncKey, driver, element).click();
        LOGGER.info(Utilities.getCurrentThreadId() + "Clicked on element with locator:" + element);
    }

    public void contextClick(String syncKey, By element) throws TimeoutException, WaitException {
        Actions action = new Actions(driver);
        action.contextClick(wait.syncElementUsing(syncKey, driver, element)).perform();
        LOGGER.info(Utilities.getCurrentThreadId()
                + "Context click performed on element with locator:" + element + " using JQuery");
    }

    public void clickByJQuery(String element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("$('" + element + "').click()");
        LOGGER.info("Clicked on element with locator:" + element + " using JQuery");
    }

    public void submitForm(String syncKey, By element) throws TimeoutException, WaitException {
        wait.syncElementUsing(syncKey, driver, element).submit();
        LOGGER.info(Utilities.getCurrentThreadId() + "Clicked on form submit button:" + element);
    }

    public void switchToSecondaryWindow(String windowTitle) throws WaitException,
            InterruptedException {
        wait.waitForTimePeriod(10000);
        LOGGER.info(Utilities.getCurrentThreadId() + "Secondary window title for switching: "
                + windowTitle);
        Set<String> windows = driver.getWindowHandles();
        LOGGER.info(Utilities.getCurrentThreadId() + "Windows=" + windows.toString());
        for (String strWindows : windows) {
            if (driver.switchTo().window(strWindows).getTitle().equals(windowTitle)) {
                LOGGER.info(Utilities.getCurrentThreadId() + "Switched to the window with title: "
                        + driver.switchTo().window(strWindows).getTitle());
                driver.switchTo().window(strWindows).manage().window().maximize();
                LOGGER.info(Utilities.getCurrentThreadId() + "Maximized the window with title "
                        + driver.switchTo().window(strWindows).getTitle());
                break;
            }
        }
    }

    public void selectOption(String syncKey, By parentLocator, String value)
            throws TimeoutException, WaitException {
        List<WebElement> element = wait.syncElementsUsing(syncKey, driver, parentLocator);
        LOGGER.info(Utilities.getCurrentThreadId() + "Size of the elements in the list="
                + element.size());
        LOGGER.info(Utilities.getCurrentThreadId() + "Elements=" + element.toString());
        for (int i = 0; i < element.size(); i++) {
            String temp = element.get(i).getText().replace((char) 0x00a0, (char) 0x0020);
            if (compare.compareExactText(value, temp.trim())) {
                LOGGER.info(Utilities.getCurrentThreadId() + " " + "Clicking on option " + value);
                element.get(i).click();
                LOGGER.info(Utilities.getCurrentThreadId() + "Successfully Clicked on option "
                        + temp);
                break;
            }
        }
    }

    public void selectFromDropDown(String syncKey, By element, String value)
            throws TimeoutException, WaitException {
        Select select = new Select(wait.syncElementUsing(syncKey, driver, element));
        select.selectByVisibleText(value);
        LOGGER.info(Utilities.getCurrentThreadId() + "Selected:" + value
                + " from drop-down with locator:" + element);
    }

    public String getText(String syncKey, By element) throws TimeoutException, WaitException {
        String actual = wait.syncElementUsing(syncKey, driver, element).getText();
        LOGGER.info(Utilities.getCurrentThreadId() + "Actual Value:" + actual);
        return actual;
    }

    public String getTitle() {
        LOGGER.info(Utilities.getCurrentThreadId() + "Title of the page:" + driver.getTitle());
        return driver.getTitle();
    }

    public String getAttributeValue(String syncKey, By element, String attribute)
            throws TimeoutException, WaitException {
        LOGGER.info(Utilities.getCurrentThreadId() + "Retrieving the attribute " + attribute
                + " of element " + element);
        
        String value = wait.syncElementUsing(syncKey, driver, element).getAttribute(attribute);
        
        LOGGER.info(Utilities.getCurrentThreadId() + "Value is \" " + value
                + " \" for attribute " + attribute);
        return value;
    }
    
    public String getAttributeValue(WebElement webElement, String attribute)
            throws TimeoutException, WaitException {
        LOGGER.info(Utilities.getCurrentThreadId() + "Retrieving the attribute " + attribute );
        
       String attributeValue= webElement.getAttribute(attribute);
        
        LOGGER.info(Utilities.getCurrentThreadId() + "Value is \" " + attributeValue
                + " \" for attribute " + attribute);
        return attributeValue;
    }
      
    public List<String> getWebElementsTextInList(String syncKey, By locator)
            throws TimeoutException, WaitException {
        LOGGER.info(Utilities.getCurrentThreadId() + "Coverting the locator into a List of String");
        List<WebElement> weblElementList = wait.syncElementsUsing(syncKey, driver, locator);
        LOGGER.info(Utilities.getCurrentThreadId() + "List of size=" + weblElementList.size()
                + " elements created");
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < weblElementList.size(); i++) {
            list.add(weblElementList.get(i).getText());
        }
        return list;
    }

    public List<WebElement> getWebElementsInList(String syncKey, By locator)
            throws TimeoutException, WaitException {
        List<WebElement> weblElementList = wait.syncElementsUsing(syncKey, driver, locator);
        
        LOGGER.info(Utilities.getCurrentThreadId() + "List of size=" + weblElementList.size());
        
        return weblElementList;
    }
    
    public int getWebElementsSize(String syncKey, By locator)
            throws TimeoutException, WaitException {
        List<WebElement> weblElementList = wait.syncElementsUsing(syncKey, driver, locator);
        
        LOGGER.info(Utilities.getCurrentThreadId() + "List of size=" + weblElementList.size());
        
        return weblElementList.size() ;
    }
    
    
    
    public Boolean getVisibiltyOfElementLocatedBy(By locator) throws WaitException {
        return wait.checkForElementVisibility(driver, locator);
    }
    
    public Boolean getInvisibilityOfElementLocatedy(By locator) throws WaitException {
    	return wait.waitForElementInVisibility(driver,locator);
    }

    public void pressModifierKey(Keys key) {
        Actions action = new Actions(driver);
        action.keyDown(key);
    }

    public void pressKeys(Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(key);
    }

    public void switchToFrame(String syncKey, By locator) throws TimeoutException, WaitException {
        driver.switchTo().frame(wait.syncElementUsing(syncKey, driver, locator));
        LOGGER.info("Switched to frame with locator:" + locator);
    }

    public void waitForEndOfAllAjaxes(){
    	wait.waitForEndOfAllAjaxes(driver);
    }
    
    public void hitEnter(By element) throws TimeoutException,
    WaitException {
    	WebElement webElement = null;
    	webElement = wait.syncElementUsing("visibility", driver, element);
    	LOGGER.info(Utilities.getCurrentThreadId() + "Element is visile to hit Enter");

    	webElement.sendKeys(Keys.RETURN);
    	LOGGER.info(Utilities.getCurrentThreadId() + "ENTER key pressed for element with locator:" + element);

    }
    
    /**
     * This method will take the default webdriver instance.
     * @param give any specific name that you would like the screenshot to be appended with. By default the name will include the current date/time as well.
     * @return the path of the screenshot name
     */
    public static String takeScreenShot(String screenShotPath){
    	try {
    		String screenshots = "screenshots";
    	String dateAndTime = DateAndTime
    			.getFormattedCurrentDateAndTime(APPLICATIONPROPERTY.getProperty("dateAndTimeFormat"));
    	File screenshot = null;
    		screenshot= ((TakesScreenshot) WEB_DRIVER).getScreenshotAs(OutputType.FILE);
    	
    	int lengthOfPath = screenShotPath.length();

		int totalLength = 260 - lengthOfPath;
		if (screenShotPath.length() > totalLength) {
			screenShotPath = screenShotPath.substring(0, totalLength);
		}
    	
    	String screenShotName = Utilities.getCurrentThreadId().replace(":", "")
    			.replace("\t", "")
    			+ screenShotPath +"-" +dateAndTime + ".png";
    	LOGGER.info(Utilities.getCurrentThreadId() + "Screenshot taken successfully. Name of file :" + screenShotName);
    	
		Utilities.createDirIfNotExists("screenshots");

    	String newSSPath = Utilities.getBaseDirPath()+"\\"+ screenshots + "\\"+ screenShotName;
    	LOGGER.info("New Screenshot Path : " +newSSPath );
    	FileUtils.copyFile(screenshot, new File(newSSPath));
    	return screenShotName;
    	
		} catch (Exception ee) {
			LOGGER.error(ee.getStackTrace());		
			LOGGER.error(new StringBuilder()
					.append(Utilities.getCurrentThreadId() + "Failed to capture screenshot ----: ")
					.append(ee.getMessage()));
		}
    	return "";

    }

}
//todo : add screenshot method that takes a snapshot based on the locator specified
