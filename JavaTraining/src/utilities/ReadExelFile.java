package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExelFile {

	public static void main(String[] args) throws IOException {

		FileInputStream readFile = new FileInputStream("sampleTest.xlsx");
		// For Windows Machine -- c:\test\sample\file\sampleTest.xlsx :
		// c://test//sample//file//sampleTest.xlsx
		// For Linux or Mac Machine -- usr/local/anshul/sample/sampleTest.xlsx

		XSSFWorkbook workbook = new XSSFWorkbook(readFile);
		XSSFSheet sheet = workbook.getSheet("SampleSheet");
		Row row;
		Cell cell;

		Iterator<Row> rowIterator = sheet.iterator();
		{
			while (rowIterator.hasNext()) {
				row = rowIterator.next();

				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();

					DataFormatter formatter = new DataFormatter();
					String text = formatter.formatCellValue(cell);

					System.out.println(text);

				}
			}
		}
	}

}
