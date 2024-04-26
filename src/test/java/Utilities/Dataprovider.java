package Utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class Dataprovider {

	@DataProvider(name="website_link")
	public String[][] getData() throws IOException{ //geting website link
		
		String filepath="C:\\Users\\2318691\\Downloads\\Main_Project 1\\Main_Project\\testdata\\Data.xlsx";
 
		String array[][]=new String[1][1];
		
		array[0][0]= ExcelUtils.getCellData(filepath,"Sheet1",6, 5);
		return array; 
				
	
}
}
