package com.generic.test.base;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.generic.drivers.init.Browser;
import com.generic.drivers.init.DriverFactory;
import com.generic.drivers.init.IDriver;
import com.generic.enums.DriverType;
import com.generic.property.PropertyManager;
import com.generic.utilities.DateAndTime;
import com.generic.utilities.Logg;
import com.generic.utilities.ReadExcel;
import com.generic.utilities.Reporter;
import com.generic.utilities.Utilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.Augmenter;


public class TestBase {

	protected static final Logger LOGG = Logg.createLogger();
	protected final static Utilities UTIL = new Utilities();
	protected static String[][] strorage = null;
	private static final String dateAndTimeFormat = "MM-dd-yyyy_hh.mm.ss";
	protected final static Properties applicationProperty = PropertyManager
			.loadApplicationPropertyFile();
	protected String expectedImagePath = Utilities.getBaseDirPath() + "\\src\\test\\resources\\com\\testdata\\expectedDICOMData\\";

	
	@BeforeTest
	@Parameters("executionType")
	protected void beforeTest(@Optional("local") String executionType,
			ITestContext context) {try{

				Browser browser = new Browser(
						applicationProperty.getProperty("browserName"),
						applicationProperty.getProperty("browserVersion"),
						Platform.WINDOWS);

				DriverFactory factory = new DriverFactory();		

				IDriver idriver = factory.getDriver(executionType);

				WebDriver driver = idriver.getDriver(browser);
				context.setAttribute(context.getCurrentXmlTest().getName(), driver);}
			catch(Exception e){
				LOGG.error("Error occured in Before Test method of the base class" + "Stacktrace : " + e.getStackTrace());			
			}
	}

	@AfterTest
	protected void afterTest(ITestContext context) {
		WebDriver webdriver = getWebDriverInstance(context);
		LOGG.info(Utilities.getCurrentThreadId() + "Closing the instance:"
				+ webdriver.toString());
		webdriver.close();
		webdriver.quit();
		
		context.removeAttribute(context.getCurrentXmlTest().getName());
	}
	
	
	@DataProvider(name = "ReadExcel")
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
			return strorage;
		}catch(Exception e){
			LOGG.error(e.getStackTrace());			
		}
		return null;
	}

	protected void logErrorMessage(Throwable ex) {
		StringWriter stw = new StringWriter();
		PrintWriter pw = new PrintWriter(stw);
		ex.printStackTrace(pw);
		LOGG.error(stw.toString());
	}

	protected static WebDriver getWebDriverInstance(ITestContext context) {
		return (WebDriver) context.getAttribute(context.getCurrentXmlTest()
				.getName());
	}
	
	@AfterMethod
	protected File afterMethod(ITestResult result) throws IOException {
		WebDriver webdriver = getWebDriverInstance(result.getTestContext());
		if (result.isSuccess()) {
			 return ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
		}
		if (DriverType.REMOTE.equals(applicationProperty.getProperty("executionType")))
			webdriver = new Augmenter().augment(webdriver);
		String dateAndTime = DateAndTime
				.getFormattedCurrentDateAndTime(dateAndTimeFormat);
		File screenshot = ((TakesScreenshot) webdriver)
				.getScreenshotAs(OutputType.FILE);
		String screenShotName = Utilities.getCurrentThreadId().replace(":", "")
				.replace("\t", "")
				+ dateAndTime + ".png";
		FileUtils.copyFile(screenshot, new File("./screenshots/"
				+ screenShotName));
		 return screenshot;
	}
}