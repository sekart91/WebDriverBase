package com.solutionstar.swaftee.utils;

import java.io.File;

public class CommonUtils {
	
	public String getCurrentWorkingDirectory()
	{		
		String workingDir = null;
		try{			
			workingDir = System.getProperty("user.dir");
		}catch(Exception e){
			e.printStackTrace();
		}
		return workingDir;
	}
	
	 public File getBrowserExecutable(String path, String fileName)
	 {
		 Boolean driverFilefound = false;
		 try{
			 File fileDirectory = new File(path);
			 File[] listOfFiles = fileDirectory.listFiles();
			 if(listOfFiles.length != 0)
			 {
				 for(int i = 0; i < listOfFiles.length; i++)
				 {
					 if(listOfFiles[i].getName().contains(fileName)) // && listOfFiles[i].canExecute()) TODO : can executable check failing in mac os, have to find a way to execute it in mac
					 {
						 return listOfFiles[i];
					 }
				 }
			 }
				 DriverUtils.getInstance().downloadFile(fileName, System.getProperty("webdriver.platform","windows"));
				 return getBrowserExecutable(path,fileName);
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 return new File("temp file");
	 }

}
