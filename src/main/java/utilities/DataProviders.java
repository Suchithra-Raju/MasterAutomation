package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	/**
	 * Gets all datas from excel
	 * @param excelName
	 * @param sheetName
	 * @return
	 * @throws IOException
	 */
	public static Object[][] getAllData(String excelName, String sheetName) throws IOException{
		String path = System.getProperty("user.dir")+"//testData//" + excelName;
		ExcelUtility xl = new ExcelUtility(path);
		
		int rownum = xl.getRowCount(sheetName);
		int colcount = xl.getCellCount(sheetName, 1);
		
		String apidata[][] = new String[rownum][colcount];
		
		for(int i=1; i<=rownum;i++) {
			for(int j=0;j<colcount;j++) {
				apidata[i-1][j]=xl.getCellData(sheetName, i, j);
			}
		}
		return apidata;
	}

	
	/**
	 * Gets single data from excel
	 * @param excelName
	 * @return
	 * @throws IOException
	 */
	public String[] getSingleData(String excelName) throws IOException{
		String path = System.getProperty("user.dir")+"//testData//" + excelName;
		ExcelUtility xl = new ExcelUtility(path);
		
		int rownum = xl.getRowCount("Sheet1");
		//int colcount = xl.getCellCount(sheetName, 1);
		
		String apidata[] = new String [rownum];
		
		for(int i=1; i<=rownum;i++) {
			
				apidata[i-1]=xl.getCellData("Sheet1", i,1);
			}
		
		return apidata;
	}

	

}
