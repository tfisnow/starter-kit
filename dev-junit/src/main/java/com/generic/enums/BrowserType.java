package com.generic.enums;

public enum BrowserType {
	
	FIREFOX("firefox"),
	INTERNETEXPLORER("internet explorer"),
	CHROME("chrome");
	
	private String typeOfBrowser;
	
	private BrowserType(String type) {
		typeOfBrowser = type ;
    }
	
	public String getBrowserValue(){
		return typeOfBrowser;
	}
	

}
