package com.generic.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.generic.property.PropertyManager;

public class ImageComparer {
	
	String basePath, compareExeLocation,compareExe ;
    private static final Logger LOGGER = Logg.createLogger();
    private static final Properties APPLICATIONPROPERTY = PropertyManager.loadApplicationPropertyFile();

    String dateAndTime;
		
	public ImageComparer(){
		
		 dateAndTime = DateAndTime
					.getFormattedCurrentDateAndTime(APPLICATIONPROPERTY.getProperty("dateAndTimeFormat"));

		basePath = System.getProperty("user.dir").replace("\\", "/");
		compareExe = "D:/Workspace/starter-kit/src/main/resources/com/drivers/";
		compareExeLocation = "D:\\Workspace\\starter-kit\\src\\main\\resources\\com\\drivers\\compare.exe";
	}
	
	
	/**
	 * @param expectedImagePath - The default path for these images will be "src\test\resources\com\testdata\expectedDICOMData"
	 * @param actualImagePath - This will be the actual image captured at the runtime using takeScreenShot api 
	 * @param diffImagePath - this will be a static white background image that will show the difference in the expected & actual image
	 * @return - returns 0 if the images are identical , else returns a float based on the difference in the pixels
	 * @throws IOException
	 */
	public float compareDICOM(String expectedImagePath, String actualImagePath, String diffImagePath) throws IOException{

		LOGGER.info(Utilities.getCurrentThreadId() + "Expected Image : " + expectedImagePath);
		LOGGER.info(Utilities.getCurrentThreadId() + "Actual  Image : " + actualImagePath);
		String newdiffImagePath = getNewDiffImage(diffImagePath);
		LOGGER.info(Utilities.getCurrentThreadId() + "Diff Image : " + newdiffImagePath);
		String value ;

		ProcessBuilder builder = new ProcessBuilder(
				compareExeLocation, "-metric", "RMSE",
				expectedImagePath, actualImagePath,
				diffImagePath);

		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		while (true) {
			line = r.readLine();
			value = line.split("\\(")[0].trim();
			LOGGER.info(Utilities.getCurrentThreadId() + "Differnce Value : " +value );

			if (r.read() == -1) {
				break;
			}

		}
		return Float.parseFloat(value);
	}

	  private String getNewDiffImage(String imgPath){
		  File dest = null;
		  
		  try{
		  File source = new File(imgPath);
		  String diffFile = imgPath.split("BaseWhite.png")[0].concat("DIFF_FILE_"+dateAndTime + ".png");
		  dest = new File(diffFile);

		  FileUtils.copyFile(source, dest);
		 
		  
        } catch (Exception e) {        	
        	LOGGER.error(e.getMessage());
	        LOGGER.error(Utilities.getCurrentThreadId() + "Image path to create a new diff file does not exist, Image path : " + imgPath );

        }
        return dest.getPath() ;
	 }

}
