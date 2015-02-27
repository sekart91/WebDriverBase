package test;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import CustomExceptions.MyCoreExceptions;
import utils.csvparser.CSVParser;
import webdriverbase.BaseDriverClass;

public class LaunchDriverTest extends BaseDriverClass {
	
	@BeforeSuite
	public void beforeMethod()
	{
		//Set the browser name for test
//		System.setProperty("webdriver.browserName", "chrome");
//		System.setProperty("webdriver.browserName", "phantomjs");
//		System.setProperty("webdriver.browserName", "ie");
		System.setProperty("webdriver.browserName", "firefox");
	}
	
	@AfterSuite
	public void afterMethod()
	{
		//Set the browser name for test
//		System.setProperty("webdriver.browserName", "chrome");
//		System.setProperty("webdriver.browserName", "phantomjs");
//		System.setProperty("webdriver.browserName", "ie");
		System.setProperty("webdriver.browserName", "firefox");
	}
	
	
	
	@Test ( description = "webdriver start test")
	public void launchHomeSearch()
	{
		 try {
			 WebDriver driver = getDriver();
			 driver.get("https://homesearch.com");
			 Thread.sleep(2000);
			 System.out.println("----Current url ---"+driver.getCurrentUrl());
			 Assert.assertEquals(driver.getCurrentUrl(), "https://homesearch.com/");
//			 driver.findElements(By.cssSelector(".list-inline li")).get(0).click();
//			 Thread.sleep(2000);
//			 System.out.println("----Current url ---"+driver.getCurrentUrl());
			 String os = System.getProperty("os.name").toLowerCase();
			 System.out.println(os);
			 
			
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Exception occured");
		}  
	}
	
	@Test ( description = "csv parser test")
	public void csvParserTest() throws MyCoreExceptions
	{
		HashMap <String , String[]> csvData = CSVParser.getCSVDataHash("testcsv");
		Assert.assertNotNull(csvData, "CSV Data hash returned as Null");
		
		HashMap <String , String> csvColumnIndex = CSVParser.getCSVHeaderHash();
		Assert.assertNotNull(csvColumnIndex, "CSV Column Index hash returned as Null");
		
		for(String key : csvData.keySet())
		{
			String[] rowArray = csvData.get(key);
			System.out.println("-------------\n" + getCSVData(rowArray, csvColumnIndex.get("name")) );
		}
	}

}
