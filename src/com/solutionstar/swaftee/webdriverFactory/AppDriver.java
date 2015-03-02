package com.solutionstar.swaftee.webdriverFactory;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import com.solutionstar.swaftee.CustomExceptions.MyCoreExceptions;
import com.solutionstar.swaftee.constants.WebDriverConstants;
import com.solutionstar.swaftee.utils.CSVParserUtils;
import com.solutionstar.swaftee.webdriverbase.AppPage;
import com.solutionstar.swaftee.webdriverhelpers.BaseDriverHelper;


public class AppDriver extends TestListenerAdapter {

	protected static Logger logger = LoggerFactory.getLogger(AppDriver.class.getName());
	
	BaseDriverHelper baseDriverHelper = new BaseDriverHelper();
	CSVParserUtils csvParser = new CSVParserUtils();
	
	 @Override
	  public void onTestFailure(ITestResult testResult) 
	  {
		  
		  captureBrowserScreenShot(testResult.getName(),getDriverfromResult(testResult));
		  logger.info("Test " + testResult.getName() + "' FAILED");
	  }
	  @Override
	  public void onTestSuccess(ITestResult testResult) 
	  {
		  logger.info("Test : " + testResult.getName() + "' PASSED");
	  }
	  public void captureBrowserScreenShot(String imageName, WebDriver webDriver)
	  {
		  DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		  Date date = new Date();
		  String curDate = dateFormat.format(date);
		  screenShot( WebDriverConstants.PATH_TO_BROWSER_SCREENSHOT + imageName + curDate+".jpg", webDriver); 
	  }
	
	  public WebDriver getDriverfromResult(ITestResult testResult)
	  {
		  Object currentClass = testResult.getInstance();
	      return ((AppDriver) currentClass).getDriver();
	  }
	@BeforeClass
	public void startBaseDriver() throws InterruptedException
	{
		logger.info("Starting BaseDrivers");
	    baseDriverHelper.startServer();
	    baseDriverHelper.startDriver();
	}
	
	  public void screenShot(String fileName, WebDriver webDriver) 
	  {
	      File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
	      try 
	      {
	        FileUtils.copyFile(scrFile, new File(fileName));
	      } 
	      catch (IOException e) 
	      {
	    	  e.printStackTrace();
	    	  logger.info("Error While taking Screen Shot");
	      }
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

	@AfterClass
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
	    baseDriverHelper.stopDriver();
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