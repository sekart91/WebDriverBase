package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import webdriverbase.BaseDriverClass;

public class LaunchDriverTest extends BaseDriverClass {
	
	@BeforeSuite
	public void beforeMethod()
	{
		//Set the browser name for test
		System.setProperty("webdriver.browserName", "phantomjs");
	}
	
	@Test
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
			e.printStackTrace();
		}  
	}

}
