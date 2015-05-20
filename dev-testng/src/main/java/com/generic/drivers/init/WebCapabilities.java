package com.generic.drivers.init;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.generic.enums.BrowserType;

public class WebCapabilities {

    private static DesiredCapabilities capabilities;

    private WebCapabilities() {
    }
    
    public static DesiredCapabilities getDesiredCapability(Browser browser){
    	
    	capabilities = getCapability(browser.getName());
    	capabilities.setBrowserName(browser.getName());
        capabilities.setPlatform(browser.getPlatform());
        capabilities.setVersion(browser.getVersion());
        capabilities.setJavascriptEnabled(true);
        return capabilities;
    }

    private static DesiredCapabilities getCapability(String browserName) {

    	if(BrowserType.CHROME.getBrowserValue().contentEquals(browserName)){
    		ChromeOptions options = new ChromeOptions();
    		options.addArguments("start-maximized", "forced-maximize-mode",
    				"no-default-browser-check", "always-authorize-plugins",
    				"test-type");
    		capabilities = DesiredCapabilities.chrome();
    		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    	} else if(BrowserType.FIREFOX.getBrowserValue().contentEquals(browserName)){
    		capabilities = DesiredCapabilities.chrome();
    	}	   else if(BrowserType.INTERNETEXPLORER.getBrowserValue().contentEquals(browserName)){
    		capabilities = DesiredCapabilities.internetExplorer();

    	}else {
    		capabilities = DesiredCapabilities.chrome();
    	}
    	return capabilities;
    }	
}
