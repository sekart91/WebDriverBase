package Utils;

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
}
