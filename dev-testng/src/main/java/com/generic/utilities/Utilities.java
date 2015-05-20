package com.generic.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import org.apache.log4j.Logger;
import com.generic.property.PropertyManager;

public class Utilities {

    private static final Logger LOGGER = Logg.createLogger();
    private static final Properties FRAMEWORKPROPERTIES = PropertyManager
            .loadApplicationPropertyFile();

    public static String getCurrentThreadId() {
        return "Thread:" + Thread.currentThread().getId() + "	-";
    }
    
    public static int randomNumberGenerator() {
        LOGGER.info(Utilities.getCurrentThreadId() + "Random number generator function");
        Random rand = new Random();
        LOGGER.info(Utilities.getCurrentThreadId() + "Generating a random number between 0 and "
                + FRAMEWORKPROPERTIES.getProperty("randomMaxInteger"));
        return rand.nextInt(1000);
    }

    public static String convertToString(int value) {
        LOGGER.info(Utilities.getCurrentThreadId() + "Integer to String Conversion function");
        LOGGER.info(Utilities.getCurrentThreadId() + "Converting the Integer value " + value
                + " to String");
        return String.valueOf(value);
    }

    public static int convertToInteger(String value) {
        LOGGER.info(Utilities.getCurrentThreadId() + "String to Integer Conversion function");
        LOGGER.info(Utilities.getCurrentThreadId() + "Coverting the String value " + value
                + " to Integer");
        return Integer.parseInt(value);
    }

    public static String[] convertListToStringArray(List<?> list) {
        LOGGER.info(Utilities.getCurrentThreadId() + "List to String Array Conversion function");
        LOGGER.info(Utilities.getCurrentThreadId() + "Size of the list obtained=" + list.size());
        Object[] obj = list.toArray();
        String[] str = new String[obj.length];
        for (int i = 0; i < obj.length; i++) {
            str[i] = (String) obj[i];
        }
        return str;
    }

    public static List<String> covert2DArrayToList(String[][] array2D) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < array2D.length; i++) {
            for (int j = 0; j < array2D[i].length; j++) {
                list.add(array2D[i][j]);
            }
        }
        return list;
    }
    
    /**
     * Gets the base path where the project resides
     * @return the base path
     */
    public static String getBaseDirPath(){
    	String path = System.getProperty("user.dir").replace("/", "\\");
        LOGGER.debug(Utilities.getCurrentThreadId() + "Base directory path : " + path );

		return path;
    	
    }
    
    /**
     * @param dirName is the name of the folder that's to be created
     * @return boolean based on the whether the file is created or not
     */
    public static boolean createDirIfNotExists(String dirName) {
		File dir = new File(dirName);
		boolean result = false;
		// if the directory does not exist, create it
		if (!dir.exists()) {
			LOGGER.debug("Creating Logs directory : " + dir);
			result= false;
		try{
			dir.mkdir();
		    result = true;
		 } catch(SecurityException se){
			 LOGGER.error(se.getStackTrace());
		 }        
		 if(result) {    
		   LOGGER.debug(dirName + " dir created");  
		 }
		}
		else
			LOGGER.debug(dirName + "folder already exists");
		return result;
	}
   //add debug statements whereever required inplace of the info statements in the entire project
}
