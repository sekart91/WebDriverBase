package com.solutionstar.swaftee.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.solutionstar.swaftee.constants.*;

public class WebDriverConfig 
{
	  public static boolean usingProxyServer() 
	  {
		    return (Boolean.valueOf(System.getProperty(WebDriverConstants.PROXY_SERVER, "true")));
	  }

	  public static boolean usingGrid() {
	    return Boolean.parseBoolean(System.getProperty(WebDriverConstants.GRID_SERVER, "false"));
	  }

	public static String getWebDriverProperty(String propertyName) throws IOException 
	{
/*		Properties property = new Properties();
    	InputStream inputStream = null;
	    String filename = "webdriverconfig.properties";
	    WebDriverConfig.class.getClassLoader();
		inputStream = WebDriverConfig.class.getClassLoader().getResourceAsStream(filename);
		
		//load a properties file from class path, inside static method
		property.load(inputStream);

        //get the property value and print it out
        System.out.println(property.getProperty("propertyName"));
		return property.getProperty("propertyName");
	*/
		if(propertyName.equalsIgnoreCase("gridserver"))
			return "localhost";
		else if(propertyName.equalsIgnoreCase("gridserverport"))
			return "4444";
		return null;
	}
}
