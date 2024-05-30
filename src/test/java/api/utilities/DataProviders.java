package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		String path = System.getProperty("user.dir")+"//testData//UserData.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rownum = xl.getRowCount("Sheet1");
		System.out.println(rownum);
		int colcount = xl.getCellCount("Sheet1", 1);
		System.out.println(colcount);
		
		String apidata[][] = new String[rownum-1][colcount];
		
		for(int i=1;i<rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j] = xl.getCellData("Sheet1",i,j);
			}
		}
		return apidata;
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException
	{
		String path = System.getProperty("user.dir")+"//testData//UserData.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rownum = xl.getRowCount("Sheet1");
		String apidata[] = new String[rownum-1];
		for(int i=1;i<=rownum-1;i++)
		{
			apidata[i-1] = xl.getCellData("Sheet1",i,1);
		}
		return apidata;
	}

}
