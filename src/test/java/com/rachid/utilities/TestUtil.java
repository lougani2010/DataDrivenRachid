package com.rachid.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.rachid.base.Base;

public class TestUtil  extends Base {
	
	public static String screenshotPath;
	public static String screenshotName;
	
	//take screenshot
	public static void captureScreenshot() throws IOException {
		
		Date d=new Date();
		screenshotName=d.toString().replace(" ", "_").replace(":", "_");
		
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\" + screenshotName));
	}
	
	//Reading data from excel
	@DataProvider(name="dp")
	public static Object[][] getData(Method m) {
		
		String sheetName=m.getName();
		int rows=excel.getRowCount(sheetName);
		int cols= excel.getColumnCount(sheetName);
		
		Object[][]data=new Object[rows-1][1] ;
		
		Hashtable<String,String> table=null;
		
		for(int rowNum=2;rowNum<=rows;rowNum++) {
			
			table=new Hashtable<String,String>();
			
			for(int colNum=0;colNum<cols;colNum++) {
				
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				
				data[rows-2][0]=table;
				
				
			}
			
		}
		
		return data;
		
	
		
	}
}
