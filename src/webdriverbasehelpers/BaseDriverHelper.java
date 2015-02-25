package webdriverbasehelpers;

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

import CustomExceptions.MyCoreExceptions;

public class BaseDriverHelper {
	
	WebDriver driver = null;
	ProxyServer proxyServer;
	Proxy proxy;
	
	public void startServer() throws InterruptedException
	   {
		  	proxyServer = new ProxyServer(0); //port number equals to zero starts the server in dynamic port
		  	try {
	       	proxyServer.start();
	       
	      // Start the server in specified host and port - TODO
//	      Map<String, String> options = new HashMap<String, String>();
//	      options.put("httpProxy", "127.0.0.1" + ":" + "3000");
//	      proxyServer.setOptions(options);
	       	
	       } catch (Exception e) {
	         String error = "Error while starting server.. " + e.getStackTrace();
	         System.err.println(error);
//	         logger.error(error);
	       }
	   }
	
	   public void startDriver() throws InterruptedException
	   {
		   try{
			    String browserName = System.getProperty("webdriver.browserName", "Chrome");  // Setting the chrome as default browser if no browser name is specified 
				System.out.println("browserName -- "+ browserName);
				DesiredCapabilities cap = null;
				SetBrowserCapabilities setBrowserCapabilities = new SetBrowserCapabilities();
				if(browserName.equalsIgnoreCase("chrome"))
				{
					cap = setBrowserCapabilities.setChromeDriver(cap);
		   			if(cap != null)
		   				driver = new ChromeDriver(cap);
		   			else
		   				throw new MyCoreExceptions("Capabilities return as Null");
				}
				else if(browserName.equalsIgnoreCase("ie"))
				{
					cap = setBrowserCapabilities.setIEDriver(cap);
		   			if(cap != null)
						driver = new InternetExplorerDriver();
		   			else
		   				throw new MyCoreExceptions("Capabilities return as Null");
				}
				else if(browserName.equalsIgnoreCase("firefox"))
				{
					cap = setBrowserCapabilities.setFirefoxDriver(cap);
		   			if(cap != null)
						driver = new FirefoxDriver(cap);
		   			else
		   				throw new MyCoreExceptions("Capabilities return as Null");
				}
				else if(browserName.equalsIgnoreCase("phantomjs"))
				{
					cap = setBrowserCapabilities.setPhomtomJsDriver(cap);
		   			if(cap != null)
						driver = new PhantomJSDriver(cap);
		   			else
		   				throw new MyCoreExceptions("Capabilities return as Null");
				}
					
				System.out.println("Starting the Browser -- "+ cap.getBrowserName());
				
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
					System.out.println("Proxy object is null");
		    	
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
	    
	    private void printCapabilities(Capabilities capabilities)
	    {
	        Map<String, ?> map = capabilities.asMap();
	        for (Entry<String, ?> entry : map.entrySet()) {
	          String key = entry.getKey();
	          Object value = entry.getValue();
	          System.out.println("\t\tkey is " + key + "\t\tvalue is " + value);
	        }
	    }	    
}
