package utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelFile {

	public static void main(String[] args) {
		
		//Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		//Create Excel Sheet
		XSSFSheet samplesheet = workbook.createSheet("SampleSheet");
		
		//Creating the Data
		Map<String, Object[]> dataSet = new TreeMap<String, Object[]>();
		dataSet.put("1", new Object[] {"ID", "NAME", "Company"});
		dataSet.put("2", new Object[] {"1", "James", "PertLine Inc"});
		dataSet.put("3", new Object[] {"2", "Maria", "SumoLogic Inc"});
		dataSet.put("4", new Object[] {"3", "Peter", "Siemens Corp."});
		dataSet.put("5", new Object[] {"4", "Julia", "Google Inc"});
		dataSet.put("6", new Object[] {"5", "Ajay", "FaceBook Inc"});
		
		//Iterate over the Data
		Set<String> set = dataSet.keySet();
		int rownum = 0;
		
		for (String key : set) {
			
			Row row = samplesheet.createRow(rownum++);
			
			Object[] data = dataSet.get(key);
			
			int cellNum = 0;
			
			for (Object value : data) {
				
				Cell cell = row.createCell(cellNum++);
				
				if (value instanceof String)
					cell.setCellValue((String)value);
				else if(value instanceof Integer)
				   cell.setCellValue((Integer)value);
				
			}
			
		}
		
		//Write Down file on HadDisk
		try {
			FileOutputStream writeFile = new FileOutputStream("sampleTest.xlsx");
			// For MacUsers /users/customDir Name/FileName
			//For Windows C:/Test/Sample/..../Filename
			// C://Test//Sample//....//filename
			
			
			workbook.write(writeFile);
			
			writeFile.close();
			System.out.println("Sample Excel file is being created Successfully");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
