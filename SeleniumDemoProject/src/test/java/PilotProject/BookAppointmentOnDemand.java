package PilotProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookAppointmentOnDemand extends TestBaseClass {

	@Test
	public void bookAppointmentTest() throws InterruptedException {

		// Open Website
		driver.get(prop.getProperty("url"));
		waitforWebPageToLoad();

		// Open Docker Appointment Page
		driver.findElement(By.linkText(prop.getProperty("OnDemandDoctor"))).click();

		// Handle New Tab Window
		Set<String> windowIds = driver.getWindowHandles();
		Iterator<String> itr = windowIds.iterator();

		String appointmentPageID = itr.next();
		String bookAppointmentPageID = itr.next();

		driver.switchTo().window(bookAppointmentPageID);

		// Verify Appointment page
//		Assert.assertEquals(driver.findElement(By.xpath(prop.getProperty("doctorverification_xpath"))),
//				prop.getProperty("OnDemandDoctor"));

		// Select Appointment Date
		selectAppointmentDate();

		// Go to Appointment
		driver.findElement(By.id(prop.getProperty("gotoAppointment_id"))).click();
		driver.findElement(By.xpath(prop.getProperty("cancelPopUp_xpath"))).click();

		// Enter Mobile Details
		driver.findElement(By.xpath(prop.getProperty("mobileNumber_xpath"))).sendKeys("9716180701");
		driver.findElement(By.xpath(prop.getProperty("continue_xpath"))).click();
		Thread.sleep(8000);

		//selectfromDropDown(driver.findElement(By.id("titleDropdown_id")), prop.getProperty("nameTitle"));
		driver.findElement(By.id(prop.getProperty("firstName"))).sendKeys(prop.getProperty("FirstName"));
		driver.findElement(By.xpath(prop.getProperty("lastName_xpath"))).sendKeys(prop.getProperty("LastName"));

		// Select DOB
		driver.findElement(By.id(prop.getProperty("dob_id"))).click();
		datePickerFromDropDownCalender("dob_id", prop.getProperty("dob"));

		// Select State
		selectfromDropDown(driver.findElement(By.id(prop.getProperty("state_id"))), prop.getProperty("StateName"));
		
		//Select City
		selectfromDropDown(driver.findElement(By.id(prop.getProperty("city_id"))), prop.getProperty("CityName"));
		Thread.sleep(10000);

	}

}
