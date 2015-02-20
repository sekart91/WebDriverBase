package webdriverbase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import webdriverbasehelpers.BaseDriverHelper;


public class BaseDriverClass {

	BaseDriverHelper baseDriverHelper = new BaseDriverHelper();
	
	@BeforeMethod
	public void BaseDriverClass1() throws InterruptedException
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
	
 }