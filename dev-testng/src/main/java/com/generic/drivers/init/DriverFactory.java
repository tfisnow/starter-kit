package com.generic.drivers.init;

import com.generic.enums.DriverType;


public class DriverFactory {

    public IDriver getDriver(String executionType) {
        if (executionType == null) {
            return null;
        }
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
