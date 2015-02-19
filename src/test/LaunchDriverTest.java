package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import webdriverbase.BaseDriverClass;

public class LaunchDriverTest extends BaseDriverClass {
	
	@Test
	public void launchHomeSearch()
	{
		 try {
			 WebDriver driver = getDriver();
			 driver.get("https://homesearch.com");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
	}

}
