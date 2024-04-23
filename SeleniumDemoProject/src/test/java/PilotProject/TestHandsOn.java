package PilotProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

public class TestHandsOn {
	
	
	
	
	@Test
	public void calendarTest() throws ParseException {
		
		/* 
		 * Date Formats
		 * dd-MM-yyyy -- 11/04/2024
		 * dd-MMM-yyy -- 11-Apr-2024
		 * dd-MMMM-yyyy -- 11-April-2024
		 */
		
		SimpleDateFormat datFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		String dob="12-07-2025";
		
		Date currentDate = new Date();
		
		Date suppliedDate = datFormat.parse(dob);
		
		if(currentDate.compareTo(suppliedDate) == 1) {
			System.out.println(currentDate.compareTo(suppliedDate));
		}else {
			System.out.println(currentDate.compareTo(suppliedDate));
		}
		
		String currentD = datFormat.format(currentDate);
		
		//Extract the Day
		String day = new SimpleDateFormat("dd").format(currentDate);
		System.out.println("Current Day : " + day);
		
		//Extract Month
		String monthNumeric = new SimpleDateFormat("MM").format(currentDate);
		System.out.println("Current Month : " + monthNumeric);
		
		String monthString = new SimpleDateFormat("MMM").format(currentDate);
		System.out.println("Current Month : " + monthString);
		
		String monthFullName = new SimpleDateFormat("MMMM").format(currentDate);
		System.out.println("Current Month : " + monthFullName);
		
		//Extract Year
		String year = new SimpleDateFormat("yyyy").format(currentDate);
		System.out.println("Current Year : " + year);
		
	}

}
