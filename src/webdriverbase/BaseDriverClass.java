package webdriverbase;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import utils.csvparser.CSVParser;
import webdriverbasehelpers.BaseDriverHelper;
import CustomExceptions.MyCoreExceptions;


public class BaseDriverClass {

	BaseDriverHelper baseDriverHelper = new BaseDriverHelper();
	
	@BeforeClass
	public void startBaseDriver() throws InterruptedException
	{
		System.out.println("Starting BaseDrivers");
	    baseDriverHelper.startServer();
	    baseDriverHelper.startDriver();
	}
	
	public WebDriver getDriver()
	{
		System.out.println("Returning BaseDriver");
		return baseDriverHelper.getDriver();
	}
	
	@AfterSuite
	public void afterMethod()
	{
		System.out.println("Stopping BaseDrivers");
		baseDriverHelper.stopDriver();
		baseDriverHelper.stopServer();		
	}
	
	public HashMap<String, String[]> getCSVDataHash(String fileName)
	{
		return CSVParser.getCSVDataHash(fileName);
	}
	
	public HashMap<String, String[]> getCSVDataHash(String fileName, Integer columnNumber)
	{
		return CSVParser.getCSVDataHash(fileName, columnNumber);
	}
	
	public HashMap<String, String[]> getCSVDataHash(String fileName, String columnName)
	{
		return CSVParser.getCSVDataHash(fileName, columnName);
	}
	
	public HashMap<String,String> getCSVHeaderHash() throws MyCoreExceptions
	{
		return CSVParser.getCSVHeaderHash();
	}
	
	public String getCSVData(String index, String[] rowArray)
	{
		return CSVParser.getCSVData(index, rowArray);
	}
	
	public String getCSVData(String[] rowArray, String index)
	{
		return CSVParser.getCSVData(rowArray, index);
	}
 }