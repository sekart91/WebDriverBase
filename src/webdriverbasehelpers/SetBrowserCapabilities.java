package webdriverbasehelpers;

import java.io.File;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import constants.WebDriverConstants;
import Utils.CommonUtils;

public class SetBrowserCapabilities {
	
	CommonUtils utils = new CommonUtils();
	
	public DesiredCapabilities setChromeDriver( DesiredCapabilities cap)
   	{
   		try{
   			String workingDir = utils.getCurrentWorkingDirectory();
   			if(workingDir == null)
   			{
   				System.out.println("Working directory in null ");
   				return cap;
   			}
   			
   			File chromeDriver = utils.getBrowserExecutable((workingDir+WebDriverConstants.PATH_TO_BROWSER_EXECUTABLE), "chrome");
   		    
   			if(chromeDriver.equals("tempfile"))
   			{
   				System.out.println("Unable to find executable file");
   				return cap;
   			}
//   			System.out.println("Chrome driver location -- "+ chromeDriver.getAbsolutePath());
   			System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());

   		    cap = DesiredCapabilities.chrome();
	   
   		}catch(Exception e){
   			e.printStackTrace();
   		}
   		return cap;
   	}
   	
   	public DesiredCapabilities setFirefoxDriver(DesiredCapabilities cap)
   	{
   		try{
   			cap = DesiredCapabilities.firefox();   	     
   		}catch(Exception e){
   			e.printStackTrace();
   		}
   		return cap;
   	}
   	
   	public DesiredCapabilities setIEDriver(DesiredCapabilities cap)
   	{
   		try{
   			String workingDir = utils.getCurrentWorkingDirectory();
   			if(workingDir == null)
   			{
   				System.out.println("Working directory in null ");
   				return cap;
   			}
   			
   			File ieDriver = utils.getBrowserExecutable((workingDir+WebDriverConstants.PATH_TO_BROWSER_EXECUTABLE), "IE");
   		    
   			if(ieDriver.equals("tempfile"))
   			{
   				System.out.println("Unable to find executable file");
   				return cap;
   			}
//			System.out.println("ieDriverLocation -- "+ ieDriverLocation.getAbsolutePath());  		      		   
   			System.setProperty("webdriver.ie.driver", ieDriver.getAbsolutePath());

			cap = DesiredCapabilities.internetExplorer();   	     
//   			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
   		 
   		}catch(Exception e){
   			e.printStackTrace();
   		}
   		return cap;
   	}
   	
   	public DesiredCapabilities setPhomtomJsDriver( DesiredCapabilities cap)
   	{
   		try{
   			String workingDir = utils.getCurrentWorkingDirectory();
   			if(workingDir == null)
   			{
   				System.out.println("Working directory in null ");
   				return cap;
   			}
   			File phantomDriver = utils.getBrowserExecutable((workingDir+WebDriverConstants.PATH_TO_BROWSER_EXECUTABLE), "phantomjs");
   			
   		    if(phantomDriver.equals("tempfile"))
   			{
   				System.out.println("Unable to find executable file");
   				return cap;
   			} 		   
//   		    System.out.println("phontomJsLocation -- "+ phantomDriver.getAbsolutePath());    
			System.setProperty("phantomjs.binary.path", phantomDriver.getAbsolutePath());
   			
   		    cap = DesiredCapabilities.phantomjs();
   		   
   		}catch(Exception e){
   			e.printStackTrace();
   		}
   		return cap;
   	}
}
