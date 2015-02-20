package webdriverbasehelpers;

import java.io.File;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

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
   		    File chromeDriverLocation = new File (workingDir+"\\chromedriver.exe");
//   		    System.out.println("chromeDriverLocation -- "+ chromeDriverLocation.getAbsolutePath());
   		    System.setProperty("webdriver.chrome.driver", chromeDriverLocation.getAbsolutePath());

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
   			File ieDriverLocation = new  File(workingDir+"\\IEDriverServer.exe");
//   			System.out.println("ieDriverLocation -- "+ ieDriverLocation.getAbsolutePath());  		   
			System.setProperty("webdriver.ie.driver", ieDriverLocation.getAbsolutePath());
   			
			cap = DesiredCapabilities.internetExplorer();   	     
   			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
   		 
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
   		    File phontomJsLocation = new File (workingDir+"\\phantomjs.exe");
//   		System.out.println("phontomJsLocation -- "+ phontomJsLocation.getAbsolutePath());
   		    System.setProperty("phantomjs.binary.path", phontomJsLocation.getAbsolutePath());

   		    cap = DesiredCapabilities.phantomjs();
   		   
   		}catch(Exception e){
   			e.printStackTrace();
   		}
   		return cap;
   	}
}
