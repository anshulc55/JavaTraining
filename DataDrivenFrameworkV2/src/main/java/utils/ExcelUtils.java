package utils;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelUtils {

	public XSSFWorkbook workbook = null;
	public FileInputStream fis = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	private XSSFCell cell = null;

	public ExcelUtils(String filePath) {
		try {
			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Get Row Data
	// Get Column Data
	
	// Get Cell Data
	public String getCellData(String sheetName, int colNumber, int rowNumber) {
		int sheetIndex = workbook.getSheetIndex(sheetName);
		if (sheetIndex == -1) {
			return "";
		}

		if (rowNumber <= 0) {
			return "";
		}
		
		if (colNumber < 0) {
			return "";
		}

		sheet = workbook.getSheetAt(sheetIndex);
		row = sheet.getRow(0);

		row = sheet.getRow(rowNumber - 1);
		if (row == null) {
			return "";
		}

		cell = row.getCell(colNumber);
		if (cell == null) {
			return "";
		}

		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
			String cellText = String.valueOf(cell.getNumericCellValue());
			return cellText;
		} else if (cell.getCellType() == CellType.BLANK) {
			return "";
		} else {
			return String.valueOf(cell.getBooleanCellValue());
		}
	}

	// Get Cell Data
	public String getCellData(String sheetName, String colName, int rowNumber) {
		int sheetIndex = workbook.getSheetIndex(sheetName);
		if (sheetIndex == -1) {
			return "";
		}

		if (rowNumber <= 0) {
			return "";
		}

		sheet = workbook.getSheetAt(sheetIndex);
		row = sheet.getRow(0);
		int colum_Num = -1;

		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
				colum_Num = i;
			}
		}

		if (colum_Num == -1) {
			return "";
		}

		row = sheet.getRow(rowNumber - 1);
		if (row == null) {
			return "";
		}

		cell = row.getCell(colum_Num);
		if (cell == null) {
			return "";
		}

		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
			String cellText = String.valueOf(cell.getNumericCellValue());
			return cellText;
		} else if (cell.getCellType() == CellType.BLANK) {
			return "";
		} else {
			return String.valueOf(cell.getBooleanCellValue());
		}
	}

	// Get Total Colums
	public int getColumnCount(String sheetName) {
		int sheetIndex = workbook.getSheetIndex(sheetName);
		if (sheetIndex == -1) {
			return 0;
		} else {
			sheet = workbook.getSheetAt(sheetIndex);
			row = sheet.getRow(0);
			return row.getLastCellNum();
		}
	}

	// To get Number of Rows
	public int getRowCount(String sheetName) {
		int sheetIndex = workbook.getSheetIndex(sheetName);
		if (sheetIndex == -1) {
			return 0;
		} else {
			sheet = workbook.getSheetAt(sheetIndex);
			return sheet.getLastRowNum() + 1;
		}
	}

}
