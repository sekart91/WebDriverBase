package test;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import webdriverbase.BaseDriverClass;
import CustomExceptions.MyCoreExceptions;

public class LaunchDriverTest extends BaseDriverClass {
	
	@BeforeSuite
	public void beforeMethod()
	{
		//Set the browser name for test
		System.setProperty("webdriver.browserName", "chrome");
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
			
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Exception occured");
		}  
	}
	
	@Test ( description = "csv parser test")
	public void csvParserTest() throws MyCoreExceptions
	{
		HashMap <String , String[]> csvData = getCSVDataHash("testcsv");
		Assert.assertNotNull(csvData, "CSV Data hash returned as Null");
		
		HashMap <String , String> csvColumnIndex = getCSVHeaderHash();
		Assert.assertNotNull(csvColumnIndex, "CSV Column Index hash returned as Null");
		
		for(String key : csvData.keySet())
		{
			String[] rowArray = csvData.get(key);
			System.out.println("-------------\n" + getCSVData(rowArray, csvColumnIndex.get("name")) );
		}
		
//		String[] keyArr = {"id","name","propid"};
		Integer[] keyArr= {0,1,2};
		HashMap <String , String[]> csvData_with_multikey = getCSVDataHash("testcsv", keyArr);
		Assert.assertNotNull(csvData_with_multikey, "CSV Data hash returned as Null");
		
		HashMap <String , String> csvColumnIndex_with_multikey = getCSVHeaderHash();
		Assert.assertNotNull(csvColumnIndex_with_multikey, "CSV Column Index hash returned as Null");
		
		for(String key : csvData_with_multikey.keySet())
		{
			String[] rowArray = csvData_with_multikey.get(key);
			System.out.println("\n key - "+ key + "\n"+ getCSVData(rowArray, csvColumnIndex_with_multikey.get("name")) );
		}
	}

}
