package com.generic.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.generic.utilities.Logg;

public class PropertyManager {

    private static final Properties PROPERTY = new Properties();
    private static final String APPLICATIONPROPERTIESPATH = "/src/test/resources/com/test/properties/";
    private static final Logger LOGGER = Logg.createLogger();
    
    //TODO Remove the hard coded path & set the java path 
    
    private PropertyManager() {
    }
  
    public static Properties loadApplicationPropertyFile() {
        try {
            PROPERTY.load(new FileInputStream(System.getProperty("user.dir")
                    + APPLICATIONPROPERTIESPATH + "application.properties"));
        } catch (IOException io) {
            LOGGER.info(
                    "IOException in the loadFrameworkPropertyFile() method of the PropertyManager class",
                    io);
            Runtime.getRuntime().halt(0);
        }
        return PROPERTY;
    }
}
