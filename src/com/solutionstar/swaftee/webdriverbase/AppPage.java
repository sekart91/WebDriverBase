package com.solutionstar.swaftee.webdriverbase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.solutionstar.swaftee.constants.WebDriverConstants;
import com.solutionstar.swaftee.utils.PageUtils;
import com.solutionstar.swaftee.webdriverFactory.AppDriver;

public class AppPage 
{
	 WebDriver driver;
	 protected static Logger logger = LoggerFactory.getLogger(AppDriver.class.getName());
	 
	 public AppPage(WebDriver driver)
	 {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	 protected static Object initializeInBase(WebDriver driver) 
	 {
		System.out.println("inside initializeInBase");
		return driver;
	 }
	
	 public void get(String url) 
	 {
		driver.get(url);
	 }
	
	 public String getCurrentUrl() 
	 {
		return driver.getCurrentUrl();
	 }
	 
	 public void deleteCookies() 
	 {
	    driver.manage().deleteAllCookies();
	 }

	 public String pageSource() 
	 {
	    return driver.getPageSource();
	 }
	
	 public boolean isElementPresent(By locator) 
	 {
	    return driver.findElements(locator).size() == 0? false : true;
	 }
	 public boolean isElementPresent(WebElement element) 
	 {
		 try
		 {
			 element.getAttribute("innerHTML");
		 }
		 catch(Exception ex)
		 {
			 return false;
		 }
	    return true;
	 }
	
	 public boolean isElementPresentAndDisplayed(WebElement element) 
	 {
		 try
		 {
			 return isElementPresent(element) && element.isDisplayed();
		 }
		 catch(Exception ex)
		 {
			 return false;
		 }
     }
	 
	 public Boolean isElementPresentInContainer(WebElement container, final By locator) 
	 {
		    Boolean isElementPresent = false;
		    if (container != null && container.findElements(locator).size() > 0)
		      isElementPresent = true;
		    return isElementPresent;
	 }
	 public void waitForVisible(WebElement element) 
	 {
		    WebDriverWait wait =
		        new WebDriverWait(this.driver,WebDriverConstants.WAIT_FOR_VISIBILITY_TIMEOUT_IN_SEC);
		    wait.until(ExpectedConditions.visibilityOf(element));
     }
	 
	 public WebElement fluentWaitByLocator(final By locator, int timeout) 
	 {
		 return PageUtils.fluentWait(locator,timeout,this.driver);
	 }
	 public void waitForPageLoad(int timeout) 
	 {
		 PageUtils.fluentWaitforPageLoad(timeout,this.driver);
		   
	  }
	  public void waitForPageLoadComplete() 
	  {
		  waitForPageLoad(WebDriverConstants.MAX_TIMEOUT_PAGE_LOAD);
		  return;
	  }
	  public void clearAndType(WebElement element, String text) 
	  {
		  element.clear();
		  element.sendKeys(text);
	  }
	  
	  public void screenShot(String fileName) 
	  {
	      File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
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
	  public void selectDropdown(WebElement element, String by, String value) 
	  {
		  Select select = new Select(element);
		  switch (by.toLowerCase()) 
		  {
		      case "index":
		        select.selectByIndex(Integer.parseInt(value));
		        break;
		      case "value":
		        select.selectByValue(value);
		        break;
		      case "text":
		        select.selectByVisibleText(value);
		        break;

		  }
	  }

}
