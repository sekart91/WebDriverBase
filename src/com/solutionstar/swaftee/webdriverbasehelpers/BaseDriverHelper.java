package com.solutionstar.swaftee.webdriverbasehelpers;

import java.lang.reflect.Method;
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
import com.solutionstar.swaftee.constants.WebDriverConstants;

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
			    String browserName = System.getProperty("webdriver.browserName", "Chrome");  // Setting the chrome as default browser if no browser name is specified 
			    logger.info("browserName -- "+ browserName);
				DesiredCapabilities cap = null;
				SetBrowserCapabilities setBrowserCapabilities = new SetBrowserCapabilities();
				browserName = browserName.toLowerCase() ;
				browserName =  WebDriverConstants.DRIVER_METHOD.containsKey(browserName) ? browserName : "chrome";
			
				Method setCapabilities = setBrowserCapabilities.getClass().getMethod(WebDriverConstants.DRIVER_METHOD.get(browserName),DesiredCapabilities.class);
				cap = (DesiredCapabilities) setCapabilities.invoke(setBrowserCapabilities, cap);
				if (cap == null)
					throw new MyCoreExceptions("Capabilities return as Null");
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
				createProxy(cap);	
				printCapabilities(cap);
			
		   }catch ( Exception e){
			   e.printStackTrace();
		   }
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
