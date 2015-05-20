package com.generic.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.generic.exceptions.WaitException;
import com.generic.page.base.PageBase;

public class SyncWebWait extends WebActions {

	
    public SyncWebWait(WebDriver driver) {
		super(driver);
		// TODO  @rishi/tahir to merge both the properties file framework & application

	}

	public String getText(String syncKey, By locator) throws TimeoutException, WaitException {
        new PageBase().doWait();
        return super.getText(syncKey, locator);
    }

    public void click(String syncKey, By element) throws TimeoutException, WaitException {
    	super.click(syncKey, element);
        new PageBase().doWait();
    }
}
