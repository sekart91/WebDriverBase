package com.solutionstar.swaftee.webdriverbase;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import com.solutionstar.swaftee.CustomExceptions.MyCoreExceptions;
import com.solutionstar.swaftee.utils.CSVParserUtils;
import com.solutionstar.swaftee.webdriverbasehelpers.BaseDriverHelper;


public class AppDriver {

	protected static Logger logger = LoggerFactory.getLogger(AppDriver.class.getName());
	
	BaseDriverHelper baseDriverHelper = new BaseDriverHelper();
	CSVParserUtils csvParser = new CSVParserUtils();
	
	
	@BeforeClass
	public void startBaseDriver() throws InterruptedException
	{
		logger.info("Starting BaseDrivers");
	    baseDriverHelper.startServer();
	    baseDriverHelper.startDriver();
	}
	
	public void startSecondaryDriver() throws InterruptedException
	{
		baseDriverHelper.startSecondaryDriver();
	}
	
	public WebDriver getDriver()
	{
		return baseDriverHelper.getDriver();
	}
	
	public WebDriver getSecondaryDriver()
	{
		return baseDriverHelper.getSecondaryDriver();
	}

	public String getPrimaryWinhandle() throws MyCoreExceptions
	{
		return baseDriverHelper.getPrimaryWinhandle();
	}
	
	public String getSecondaryWinhandle() throws MyCoreExceptions
	{
		return baseDriverHelper.getSecondaryWinhandle();
	}
	
	public Logger getLogger()
	{
			return logger;
	}
	
	public Logger getLogger(Class<?> className)
	{
		Logger newLogger =baseDriverHelper.getLogger(className);
		if(newLogger != null)
			return newLogger;
		else
		{
			logger.warn("Logger initialization with class name provided failed. Returning default logger");
			return logger;
		}
	}
	
	public HashMap<String, String[]> getCSVDataHash(String fileName)
	{
		return csvParser.getCSVDataHash(fileName);
	}
	
	public HashMap<String, String[]> getCSVDataHash(String fileName, Integer columnNumber)
	{
		return csvParser.getCSVDataHash(fileName, columnNumber);
	}
	
	public HashMap<String, String[]> getCSVDataHash(String fileName, String columnName)
	{
		return csvParser.getCSVDataHash(fileName, columnName);
	}
	
	public HashMap<String, String[]> getCSVDataHash(String fileName, String[] rowArray)
	{
		return csvParser.getCSVDataHash(fileName, rowArray);
	}
	
	public HashMap<String, String[]> getCSVDataHash(String fileName, Integer[] rowArray)
	{
		return csvParser.getCSVDataHash(fileName, rowArray);
	}
	
	public HashMap<String,String> getCSVHeaderHash() throws MyCoreExceptions
	{
		return csvParser.getCSVHeaderHash();
	}
	
	public String getCSVData(String index, String[] rowArray)
	{
		return csvParser.getCSVData(index, rowArray);
	}
	
	public String getCSVData(String[] rowArray, String index)
	{
		return csvParser.getCSVData(rowArray, index);
	}
	
	@AfterSuite
	public void afterMethod()
	{
		logger.info("Stopping BaseDrivers");
		baseDriverHelper.stopDriver();
		baseDriverHelper.stopServer();		
	}
	
	public String getBrowserName() 
	{
	    return getDriver() != null ? ((RemoteWebDriver) getDriver()).getCapabilities().getBrowserName() : null;
	}

	protected void stopDriver() 
	{
	    if (getDriver() != null) 
	    {
	      getDriver().quit();
	      setDriver(null);
	    }
	    if(getSecondaryDriver() != null)
	    {
	      getSecondaryDriver().quit();
	      setSecondaryDriver(null);	    	
	    }
     }

	  public void setDriver(WebDriver driver) 
	  {
		  baseDriverHelper.setDriver(driver);
	  }
	  
	  public void setSecondaryDriver(WebDriver driver) 
	  {
		  baseDriverHelper.setSecondaryDriver(driver);
	  }
 }