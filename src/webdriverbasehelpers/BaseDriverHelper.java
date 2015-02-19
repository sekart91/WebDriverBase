package webdriverbasehelpers;

import net.lightbody.bmp.proxy.ProxyServer;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseDriverHelper {
	
	WebDriver driver = null;
	ProxyServer proxyServer;
	Proxy proxy;
	
	public void startServer() throws InterruptedException
	   {
		  	proxyServer = new ProxyServer(0); //port number equals to zero starts the server in dynamic port
	       try {
	       	proxyServer.start();
	       	System.out.println("Server - "+ proxyServer.getPort());
	       	
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
	
	   public void startDriver(String browserName) throws InterruptedException
	   {
		   try{
			
			DesiredCapabilities cap = null;
			if(browserName.equalsIgnoreCase("chrome"))
			{
				cap = setChromeDriver(cap);
				driver = new ChromeDriver(cap);
			}
			else if(browserName.equalsIgnoreCase("firefox"))
			{
				cap = setFirefoxDriver(cap);
				driver = new FirefoxDriver(cap);
			}
			
			createProxy(cap);	
			
		   }catch ( Exception e){
			   e.printStackTrace();
		   }
		}
	    
	   	public DesiredCapabilities setChromeDriver( DesiredCapabilities cap)
	   	{
	   		try{
	   		 String chromeDriverLocation = "C:\\Users\\mm\\Downloads\\chromedriver_win32";
			 System.out.println("chromeDriverLocation -- "+ chromeDriverLocation);
			 System.setProperty(" webdriver.chrome.driver", chromeDriverLocation);

			 cap = DesiredCapabilities.chrome();
			 System.out.println("Starting the Browser -- "+ cap.getBrowserName());
			   
	   		}catch(Exception e){
	   			e.printStackTrace();
	   		}
	   		return cap;
	   	}
	   	
	   	public DesiredCapabilities setFirefoxDriver(DesiredCapabilities cap)
	   	{
	   		try{
	   			cap = DesiredCapabilities.firefox();   	     
	   			System.out.println("Starting the Browser -- "+ cap.getBrowserName());
	   		 
	   		}catch(Exception e){
	   			e.printStackTrace();
	   		}
	   		return cap;
	   	}
	   	
	   	public DesiredCapabilities setIEDriver(DesiredCapabilities cap)
	   	{
	   		try{
	   			cap = DesiredCapabilities.internetExplorer();   	     
	   			System.out.println("Starting the Browser -- "+ cap.getBrowserName());
	   		 
	   		}catch(Exception e){
	   			e.printStackTrace();
	   		}
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
	        	driver.quit();
	        	this.driver = null;
	        }
	    }
	    
	    public WebDriver getDriver()
	    {
	    	return this.driver;
	    }
	    
}
