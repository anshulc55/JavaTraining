package PilotProject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookAppointment extends TestBaseClass {

	@Test
	public void bookAppointmentTest() throws InterruptedException {

		// Read Data and Locators from Properties file.
		driver.get(prop.getProperty("url"));
		waitforWebPageToLoad();

		// Open Consultation Pop-Up
		driver.findElement(By.linkText(prop.getProperty("doctor_name"))).click();
		waitforWebPageToLoad();
		driver.findElement(By.xpath(prop.getProperty("Consultation_btn"))).click();

		// Validate presence and visibility of Name and any other field
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prop.getProperty("name_id"))));
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("email_id"))));

		// Enter Name, Email, Mobile
		driver.findElement(By.id(prop.getProperty("name_id"))).sendKeys(prop.getProperty("patient_name"));
		driver.findElement(By.id(prop.getProperty("email_id"))).sendKeys(prop.getProperty("patient_email"));
		driver.findElement(By.id(prop.getProperty("mobile_id"))).sendKeys(prop.getProperty("patient_mobile"));

		// Validate the Text Below the Date Field
		WebElement dateText = driver.findElement(By.xpath(prop.getProperty("dateText_xpath")));
		Assert.assertEquals(dateText.getText(),
				"Preferred Date and Time is subject to change. Our support team will be in contact with you within 24 hours.");

		// Validate Options in Gender
		WebElement genderDropDown = driver.findElement(By.id(prop.getProperty("gender_id")));
		Select dropdown = new Select(genderDropDown);
		dropdown.getOptions();
		for (WebElement dropDownValue : dropdown.getOptions()) {
			System.out.println(dropDownValue.getText());
		}

		dropdown.selectByVisibleText("Male");

		// Enter Date of Birth 12/12/2005
		datePickerFromDropDownCalender("dobCalendar_id", prop.getProperty("dob"));
		// Thread.sleep(8000);

		// Validate the presence and visibility of Preferred Visiting Field 1
		wait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(prop.getProperty("calenderPrederDate1_id"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("calenderPrederDate1_id"))));

		// Select Preferred Visiting Date - Tomorrow
		datePickerFromSlidingCalender(prop.getProperty("appointment1_date"));
		// Thread.sleep(8000);

		// Validate the presence and visibility of Preferred Visiting Field 2
		// Select Preferred Visiting Date - Day After Tomorrow

		// Verify the Visibility of UHID
		Assert.assertFalse(driver.findElement(By.id(prop.getProperty("uhid_id"))).isDisplayed());

		// Check Yes Radio Button
		driver.findElement(By.id(prop.getProperty("radioBtnYes_id"))).click();
		Assert.assertTrue(driver.findElement(By.id(prop.getProperty("uhid_id"))).isDisplayed());

		// Enter UHID
		driver.findElement(By.id(prop.getProperty("uhid_id"))).sendKeys(prop.getProperty("UHID"));

		// Click No Radio Button and Check UHID
		driver.findElement(By.id(prop.getProperty("radioBtnNo_id"))).click();
		Assert.assertFalse(driver.findElement(By.id(prop.getProperty("uhid_id"))).isDisplayed());

		// Click Yes , Verify UHID Field and Compare the Existing UHID
		driver.findElement(By.id(prop.getProperty("radioBtnYes_id"))).click();
		Assert.assertTrue(driver.findElement(By.id(prop.getProperty("uhid_id"))).isDisplayed());
		String actualUHID = driver.findElement(By.id(prop.getProperty("uhid_id"))).getAttribute("value");
		String expectedUHID = prop.getProperty("UHID");
		Thread.sleep(5000);
		Assert.assertEquals(actualUHID, expectedUHID);

	}

}
