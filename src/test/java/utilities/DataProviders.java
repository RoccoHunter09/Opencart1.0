package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	//we can have multiple data provider
	
	//DataProvider1
	
	@DataProvider(name="loginData")
	public String[][] getdata() throws IOException{
		
		String path=".\\testData\\sbiroi.xlsx"; //TAKING XL FILE from test data
		
		excelUtility xlUtil=new excelUtility(path); //creating an object for excelUtility
		
		int totalrows=xlUtil.getRowCount("Sheet1");
		int totalcells=xlUtil.getCellCount("Sheet1", 1);
		
		String LoginData[][]=new String[totalrows][totalcells];//created for two dimensional array to store
		for (int i=1;i<=totalrows;i++) {  //read the data from excel and store it in 2 dimensional array
			for(int j=0;j<totalcells;j++) {
				
				LoginData[i-1][j]=xlUtil.getCellData("Sheet1", i, j);
			}
			
		}
		
		
		return LoginData;
	}
	
}