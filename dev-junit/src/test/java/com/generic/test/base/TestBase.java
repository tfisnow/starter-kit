package com.generic.test.base;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Properties;
import org.apache.log4j.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.Platform;

import com.generic.drivers.init.Browser;
import com.generic.drivers.init.DriverFactory;
import com.generic.drivers.init.IDriver;
import com.generic.property.PropertyManager;
import com.generic.utilities.Logg;
import com.generic.utilities.ReadExcel;
import com.generic.utilities.Utilities;

public class TestBase {

	protected static final Logger LOGG = Logg.createLogger();
	protected final static Utilities UTIL = new Utilities();
	protected static String[][] strorage = null;
	protected final static Properties APP_PROPERTY = PropertyManager
			.loadApplicationPropertyFile();
	protected WebDriver webdriver;	
	
	protected String expectedImagePath = Utilities.getBaseDirPath() + "\\src\\test\\resources\\com\\testdata\\expectedDICOMData\\";
		
	@Before
	public void beforeTest()  {
		try{
		LOGG.info("BEFORE TEST");
		Browser browser = new Browser(
				APP_PROPERTY.getProperty("browserName"),
				APP_PROPERTY.getProperty("browserVersion"),
				Platform.WINDOWS);
		
		DriverFactory factory = new DriverFactory();		
		
		IDriver idriver = factory.getDriver("local");
		
		webdriver  = idriver.getDriver(browser);}

		catch(Exception e){
			LOGG.error("Error occured in Before Test method of the base class" + "Stacktrace : " + e.getStackTrace());			
		}
}

	@After
	public void afterTest() {
		LOGG.info(Utilities.getCurrentThreadId() + "Closing the instance:"
				+ webdriver.toString());
		webdriver.close();
		webdriver.quit();		
	}
		
	
	public String[][] readDataFromExcel(Method m) {
		try{

			LOGG.info(Utilities.getCurrentThreadId() + "Data Provider: Read Excel");
			LOGG.info(Utilities.getCurrentThreadId()
					+ "Data Provider: Running for Method: " + m.getName());
			if ("enterAndValidateUniversityData".equals(m.getName())) {
				strorage = ReadExcel.readTestData("Customer");
				LOGG.info(Utilities.getCurrentThreadId()
						+ "Data Provider: Retrieved data from the Customer Sheet of Test Data Excel");
			} else if ("".equals(m.getName())) {
				strorage = ReadExcel.readTestData("Sheet2");
			} else {
				LOGG.info(Utilities.getCurrentThreadId()
						+ "NO MATCHING METHOD FOUND. PLEASE CHECK THE METHOD NAME IN THE DATA PROVIDER");
			}
			return strorage;	}
		catch(Exception e){
			LOGG.error(e.getStackTrace());			
		}
		return null;

	}
}