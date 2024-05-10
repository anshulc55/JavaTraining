package runner;

import org.json.simple.JSONObject;

import utils.ExcelUtils;

public class ExcelReader {

	// Read Excel File

	public int getDataSets(String sheetName, String dataFlag, String filePath) {
		ExcelUtils XlsReader = new ExcelUtils(filePath);
		int flagRowNumber = 1;

		while (!XlsReader.getCellData(sheetName, 0, flagRowNumber).equalsIgnoreCase(dataFlag)) {
			flagRowNumber++;
		}
		int dataStartRowNumber = flagRowNumber + 2;
		int totalRows = 0;

		while (!XlsReader.getCellData(sheetName, 0, dataStartRowNumber).equals("")) {
			totalRows++;
			dataStartRowNumber++;
		}
		System.out.println("Total Rows :: " + totalRows);
		return totalRows;
	}

	public JSONObject getTestData(String sheetName, String dataFlag, String filePath, int iteration) {
		ExcelUtils XlsReader = new ExcelUtils(filePath);
		int flagRowNumber = 1;

		while (!XlsReader.getCellData(sheetName, 0, flagRowNumber).equalsIgnoreCase(dataFlag)) {
			flagRowNumber++;
		}

		int dataStartRowNumber = flagRowNumber + 2;
		int colStartRowNumber = flagRowNumber + 1;
		int index = 1;

		while (!XlsReader.getCellData(sheetName, 0, dataStartRowNumber).equals("")) {
			int colNumber = 0;
			JSONObject json = new JSONObject();
			if (index == iteration) {
				while (!XlsReader.getCellData(sheetName, colNumber, dataStartRowNumber).equals("")) {
					String data = XlsReader.getCellData(sheetName, colNumber, dataStartRowNumber);
					String column = XlsReader.getCellData(sheetName, colNumber, colStartRowNumber);
					// System.out.println(column + " :: " + data);
					json.put(column, data);
					colNumber++;
				}
				return json;
			} else {
				index++;
			}
			dataStartRowNumber++;
		}
		return new JSONObject();
	}

//	public static void main(String[] args) {
//		String filePath = System.getProperty("user.dir")
//				+ "/src/test/resources/projectJSONs/XLS_Data/TestExcelData.xlsx";
//		ExcelUtils XlsReader = new ExcelUtils(filePath);
//		
//
//		String sheetName = "Manage Stocks";
//		String dataFlag = "sellexistingstock";
//		int iteration = 2;
//
//		int flagRowNumber = 1;
//
//		while (!XlsReader.getCellData(sheetName, 0, flagRowNumber).equalsIgnoreCase(dataFlag)) {
//			flagRowNumber++;
//		}
//		// System.out.println("Row Number :: " + flagRowNumber);
//
//		int dataStartRowNumber = flagRowNumber + 2;
//		int colStartRowNumber = flagRowNumber + 1;
//		int index = 1;
//		
//		ExcelReader exl = new ExcelReader();
//		System.out.println(exl.getDataSets(sheetName, dataFlag, filePath));
//
//		System.out.println("************************");
//		while (!XlsReader.getCellData(sheetName, 0, dataStartRowNumber).equals("")) {
//			int colNumber = 0;
//			if (index == iteration) {
//				while (!XlsReader.getCellData(sheetName, colNumber, dataStartRowNumber).equals("")) {
//					String data = XlsReader.getCellData(sheetName, colNumber, dataStartRowNumber);
//					String column = XlsReader.getCellData(sheetName, colNumber, colStartRowNumber);
//					//System.out.println(column + " :: " + data);
//					colNumber++;
//				}
//				break;
//			} else {
//				index++;
//			}
//			dataStartRowNumber++;
//		}
//	}

}
