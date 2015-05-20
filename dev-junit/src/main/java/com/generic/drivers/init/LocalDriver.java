package com.generic.drivers.init;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.generic.enums.BrowserType;
import com.generic.utilities.Logg;
import com.generic.utilities.Utilities;

public class LocalDriver implements IDriver {

    private static final Logger LOGGER = Logg.createLogger();

    public WebDriver getDriver(Browser browser) {
        WebDriver driver = null;
        LOGGER.info(Utilities.getCurrentThreadId() + "** "+browser.getName()+ " Browser**");        

        if (BrowserType.INTERNETEXPLORER.getBrowserValue().equals(browser.getName())) {
            System.setProperty("webdriver.ie.driver",
                    "src/main/resources/com/drivers/IEDriverServer.exe");
         
            DesiredCapabilities capabilities = WebCapabilities.getDesiredCapability(browser);
            
            driver = new InternetExplorerDriver(capabilities);
            
        } else if (BrowserType.FIREFOX.getBrowserValue().equals(browser.getName())) {
            
            DesiredCapabilities capabilities = WebCapabilities.getDesiredCapability(browser);
           
            driver = new FirefoxDriver(capabilities);
        } else if (BrowserType.CHROME.getBrowserValue().equals(browser.getName())) {
         
            System.setProperty("webdriver.chrome.driver",
                    "src/main/resources/com/drivers/chromedriver.exe");
            DesiredCapabilities capabilities = WebCapabilities.getDesiredCapability(browser);
            
            driver = new ChromeDriver(capabilities);
        }
        
        LOGGER.info(Utilities.getCurrentThreadId()
                + "Instantiating/Launching the "+browser.getName()+" Browser");
        LOGGER.info(Utilities.getCurrentThreadId() + "Returning the local instance of:"
                + driver.toString());
        return driver;
    }

}

//TODO : Remove the null pointer exception
/*FAILED CONFIGURATION: @BeforeTest beforeTest("local", org.testng.TestRunner@41d4702d)
java.lang.NullPointerException
	at com.generic.drivers.init.LocalDriver.getDriver(LocalDriver.java:48)*/