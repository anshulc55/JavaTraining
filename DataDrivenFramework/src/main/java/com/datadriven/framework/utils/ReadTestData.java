package com.datadriven.framework.utils;

/*
 * 
 * This class is not going to be used in Framework.
 * 
 */
public class ReadTestData {

	public static void main(String args[]) {
		ReadExcelDataFile readdata = new ReadExcelDataFile(
				System.getProperty("user.dir") + "/src/main/java/testData/TestData_Testmanagement.xlsx");
		String sheetName = "Feature 1";
		String testName = "Test Three";

		//Find Start Row of TestCase
		int startRowNum = 0;
		while (!readdata.getCellData(sheetName, 0, startRowNum).equalsIgnoreCase(testName)) {
			startRowNum++;
		}
		System.out.println("Test Starts from Row Number : " + startRowNum);
		
		int startTestColumn = startRowNum+1;
		int startTestRow = startRowNum+2;
		
		//Find Number of Rows of TestCase
		int rows = 0;
		while (!readdata.getCellData(sheetName, 0, startTestRow+rows).equals("")) {
			rows++;
		}
		System.out.println("Total Numbe of Rows in Test : " +testName + " is - " +rows);
		
		//Find Number of Columns in Test
		int colmns=0;
		while (!readdata.getCellData(sheetName, colmns, startTestColumn).equals("")) {
			colmns++;
		}
		System.out.println("Total Number of Columns in Test : " +testName + " is - " +colmns);
		
		for (int rowNumber=startTestRow; rowNumber<=startTestColumn+rows; rowNumber++) {
			for (int colNumber=0; colNumber<colmns; colNumber++) {
				System.out.println(readdata.getCellData(sheetName, colNumber, rowNumber));
			}
			
		}
	}
	
	

}
