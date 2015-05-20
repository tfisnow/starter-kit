package com.generic;

import org.apache.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.generic.utilities.Logg;
import com.generic.validateorders.WebAppTest;


public class TestRunner  {
    private static final Logger LOGGER = Logg.createLogger();

	private TestRunner(){
		// this doesn't really have any purpose!
	}
	
	 public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(WebAppTest.class);
	      for (Failure failure : result.getFailures()) {
	    	  LOGGER.error(failure.toString());
	      }
	      LOGGER.info(result.wasSuccessful());
	   }

}