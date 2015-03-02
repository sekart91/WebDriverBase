package com.solutionstar.swaftee.webdriverhelpers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import net.lightbody.bmp.proxy.ProxyServer;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.browserlaunchers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solutionstar.swaftee.CustomExceptions.MyCoreExceptions;
import com.solutionstar.swaftee.config.WebDriverConfig;
import com.solutionstar.swaftee.constants.WebDriverConstants;

import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseDriverHelper {
	
	WebDriver driver = null;
	ProxyServer proxyServer = null;
	Proxy proxy;
	
	Logger logger = getLogger(this.getClass());
	
	public void startServer() throws InterruptedException
	   {
			if(proxyServer !=  null)
				return;
		  	proxyServer = new ProxyServer(0); //port number equals to zero starts the server in dynamic port
		  	try {
	       	proxyServer.start();
	       
	      // Start the server in specified host and port - TODO
//	      Map<String, String> options = new HashMap<String, String>();
//	      options.put("httpProxy", "127.0.0.1" + ":" + "3000");
//	      proxyServer.setOptions(options);
	       	
	       } catch (Exception e) {
	         String error = "Error while starting server.. " + e.getStackTrace();
	         logger.error(error);
//	         logger.error(error);
	       }
	   }
	
	   public void startDriver() throws InterruptedException
	   {
		   try{
			    if(driver != null)
			    	return;
			    DesiredCapabilities cap = startDriver(System.getProperty("webdriver.browserName", "Chrome"));
				createProxy(cap);	
				printCapabilities(cap);
			
		   }catch ( Exception e){
			   e.printStackTrace();
		   }
		}
	    
	   
	   public DesiredCapabilities startDriver(String browserName) throws Exception
	   {
		    browserName = browserName.toLowerCase();
	   		logger.info("browserName -- "+ browserName);
	   		DesiredCapabilities cap = setDriverCapabilities(browserName);
			if(WebDriverConfig.usingGrid())
			{
				cap = setRemoteWebDriver(browserName);
			}
			else
			{
				cap = setWebDriver(browserName);
			}
			return cap;
	   }
	   

	   private DesiredCapabilities setRemoteDriverCapabilities(String browserName) throws Exception
	   {
		 	DesiredCapabilities capab = new DesiredCapabilities();
			capab.setBrowserName(browserName);
			capab.setVersion("9.0.1");
			capab.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			
			return capab;
	   }
	
	   private DesiredCapabilities setDriverCapabilities(String browserName) throws Exception
	   {
		   DesiredCapabilities cap = null;
		   SetBrowserCapabilities setBrowserCapabilities = new SetBrowserCapabilities();
		   browserName =  WebDriverConstants.DRIVER_METHOD.containsKey(browserName) ? browserName : "chrome";
	
		   Method setCapabilities = setBrowserCapabilities.getClass().getMethod(WebDriverConstants.DRIVER_METHOD.get(browserName),DesiredCapabilities.class);
		   return (DesiredCapabilities) setCapabilities.invoke(setBrowserCapabilities, cap);
	   }
	   
	   private DesiredCapabilities setWebDriver(String browserName) throws Exception
	   {
		   DesiredCapabilities cap = setDriverCapabilities(browserName);
			switch (browserName) 
		    {
			     case "chrome":
					driver = new ChromeDriver(cap);
		   			break;
				case "ie":
					driver = new InternetExplorerDriver();
		   			break;
				case "firefox":
					driver = new FirefoxDriver(cap);
		   			break;
				case "phantomjs":
					driver = new PhantomJSDriver(cap);
		   			break;
				default:
		            throw new IllegalArgumentException("Invalid Argument for browser name : " + browserName);
		    }
			return cap;
	   }
	   
	   private DesiredCapabilities setRemoteWebDriver(String browserName) throws Exception
	   {
		    
		    DesiredCapabilities cap = setRemoteDriverCapabilities(browserName);
			driver = new RemoteWebDriver(new URL("http://"+ WebDriverConfig.getWebDriverProperty("gridserver") +":"+ WebDriverConfig.getWebDriverProperty("gridserverport") +"/wd/hub"),cap);
			return cap;
	   }   
	    private Proxy createProxyObject() {
	        try {
	          proxy = proxyServer.seleniumProxy();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        // set server properties.
	        proxyServer.setCaptureHeaders(true);// capture headers
	        proxyServer.setCaptureContent(true);// capture content.

	        return proxy;
	      }
	    
	    private void createProxy(DesiredCapabilities cap)
	    {
	    	try{
	    		Proxy proxy = createProxyObject();
				if (proxy != null)
				      cap.setCapability(CapabilityType.PROXY, proxy);
				else
					logger.info("Proxy object is null");
		    	
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    public void stopServer()
	    {
	    	try{
	    		proxyServer.stop();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    public void stopDriver() 
	    {
	        if (driver != null) 
	        {
	        	this.driver.quit();
	        	this.driver = null;
	        }
	    }
	    
	    public WebDriver getDriver()
	    {
	    	return this.driver;
	    }
	    public void setDriver(Object obj)
	    {
	    	this.driver = (WebDriver) obj;
	    }
	    
	    private void printCapabilities(Capabilities capabilities)
	    {
	        Map<String, ?> map = capabilities.asMap();
	        for (Entry<String, ?> entry : map.entrySet()) {
	          String key = entry.getKey();
	          Object value = entry.getValue();
	          logger.info("\t\tkey is " + key + "\t\tvalue is " + value);
	        }
	    }

		public Logger getLogger(Class<?> className) 
		{
			Logger logger = null;
			try{
				logger = LoggerFactory.getLogger(className);				
			}catch(Exception e){
				e.printStackTrace();
			}
			return logger;
		}	    
}
