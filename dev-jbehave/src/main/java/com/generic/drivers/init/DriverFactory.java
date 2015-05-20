package com.generic.drivers.init;

import org.apache.log4j.Logger;

import com.generic.enums.DriverType;
import com.generic.utilities.Logg;
import com.generic.utilities.Utilities;


public class DriverFactory {
	protected static final Logger LOGGER = Logg.createLogger();


    public IDriver getDriver(String executionType) {
        if (executionType == null) {
            LOGGER.error(Utilities.getCurrentThreadId() + "** Execution Type is NULL **");        
            return null;
        }
        
        LOGGER.info(Utilities.getCurrentThreadId() + "** Execution Type is " +  executionType + " ** ");        

        if (DriverType.REMOTE.equals(executionType)) {
            return new RemoteDriver();
        } else if (DriverType.LOCAL.equals(executionType)) {
            return new LocalDriver();
        } else if (DriverType.MOBILE.equals(executionType)) {
            return new MobileDriver();
        }
         return new LocalDriver();
    }

}
