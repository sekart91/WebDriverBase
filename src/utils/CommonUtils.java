package utils;

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
		 try{
			 File fileDirectory = new File(path);
			 File[] listOfFiles = fileDirectory.listFiles();
			 if(listOfFiles.length != 0)
			 {
				 for(int i = 0; i < listOfFiles.length; i++)
				 {
					 if(listOfFiles[i].getName().contains(fileName) && listOfFiles[i].canExecute())
						 return listOfFiles[i];
				 }
			 }
			
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return new File("tempfile");
	 }

}
