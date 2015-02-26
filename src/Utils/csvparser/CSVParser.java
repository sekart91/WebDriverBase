package Utils.csvparser;

import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Utils.CommonUtils;
import CustomExceptions.MyCoreExceptions;

import com.opencsv.CSVReader;

import constants.CSVParserConstants;

public class CSVParser {
	
	public static HashMap<String, String[]> csvDataHash;
	public static HashMap<String, String> csvColumnIndexHash;
	public static int headerRow = 0;
	
	static CommonUtils utils ;
	
	public static void initializeConstans()
	{
		utils = new CommonUtils();
		csvDataHash = new HashMap<String, String[]>();
		csvColumnIndexHash = new HashMap<String,String>(); 
	}

	public static HashMap<String, String[]> getCSVDataHash(String fileName)
	{
		try{
			initializeConstans();
			 if(utils == null)
				 System.out.println("Utils obj is null");
			 
			 CSVReader reader = new CSVReader(new FileReader(utils.getCurrentWorkingDirectory() + CSVParserConstants.PATH_TO_FILE + fileName + ".csv"));
			 List<String[]> rowEntries = reader.readAll();
			 for(String[] row : rowEntries)
			 {
				 if(headerRow ++ == 0)
					 createCSVHeaderHash(row);
				 else
					 csvDataHash.put(row[0], row);
			 }
			 reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return csvDataHash;
	}

	@SuppressWarnings("resource")
	public static HashMap<String, String[]> getCSVDataHash(String fileName, int columnNumber)
	{
		try{
			 initializeConstans();
			 CSVReader reader = new CSVReader(new FileReader(utils.getCurrentWorkingDirectory() + CSVParserConstants.PATH_TO_FILE + fileName + ".csv"));
			 List<String[]> rowEntries = reader.readAll();
			 
			 if(rowEntries.get(0).length < columnNumber)
				 throw new MyCoreExceptions("Column Number Provided is out of data range in the file given");
			 
			 for(String[] row : rowEntries)
			 { 
				 if(headerRow ++ == 0)
					 createCSVHeaderHash(row);
				 else
					 csvDataHash.put(row[columnNumber], row);
			 }
			 reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return csvDataHash;
	}
	
	@SuppressWarnings("resource")
	public static HashMap<String, String[]> getCSVDataHash(String fileName, String columnName)
	{
		try{
			 initializeConstans();
			 int columnNumber = -1;
			 CSVReader reader = new CSVReader(new FileReader(utils.getCurrentWorkingDirectory() + CSVParserConstants.PATH_TO_FILE + fileName + ".csv"));
			 List<String[]> rowEntries = reader.readAll();
			 
			 columnNumber = Arrays.asList(rowEntries.get(0)).indexOf(columnName);
			 
			 if(rowEntries.get(0).length < columnNumber)
				 throw new MyCoreExceptions("Column Number Provided is out of data range in the file given");
			 
			 for(String[] row : rowEntries)
			 {
				 if(headerRow ++ == 0)
					 createCSVHeaderHash(row);
				 else
					 csvDataHash.put(row[columnNumber], row);
			 }
			 reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return csvDataHash;
	}
	
	private static void createCSVHeaderHash(String[] row)
	{
		try{
			for(int i=0; i<row.length; i++)
			{
				csvColumnIndexHash.put(row[i], String.valueOf(i));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static HashMap<String,String> getCSVHeaderHash() throws MyCoreExceptions
	{
		try{
			return csvColumnIndexHash;
		}catch(Exception e){
			throw new MyCoreExceptions("Exception while getting the CSV Column header hash");
		}
	}		
	
	public static String getCSVData(String[] rowArray, String index)
	{
		String cellData = null;
		try{
			cellData = rowArray[Integer.parseInt(index)];
		}catch(Exception e){
			e.printStackTrace();
		}
		return cellData;
	}
	
	public static String getCSVData(String index, String[] rowArray)
	{
		String cellData = null;
		try{
			cellData = rowArray[Integer.parseInt(index)];
		}catch(Exception e){
			e.printStackTrace();
		}
		return cellData;
	}
}
