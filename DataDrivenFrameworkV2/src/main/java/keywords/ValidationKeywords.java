package keywords;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ValidationKeywords extends GenericKeywords{

	
	public void validateTitle(String expectedTitle) {
		logInfo("Expected Title : " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}
	
	public void validateSelectedValueInDropDown(String locatorKey, String value) {
		Select dropdown = new Select(getElement(locatorKey));
		String selectedValue = dropdown.getFirstSelectedOption().getText();
		logInfo("Selected Value in DropDown : " + selectedValue);
		
		if(!selectedValue.equals(value)) {
			reportFailure("Entered "+ value + " is not availble in Portfolio List", true);
		}
	}
	
	public void validateSelectedValueNotInDropDown(String locatorKey, String value) {
		Select dropdown = new Select(getElement(locatorKey));
		String selectedValue = dropdown.getFirstSelectedOption().getText();
		if(selectedValue.equals(value)) {
			reportFailure("Portfolio "+ value + " is  availble in Portfolio List", true);
		}
	}

	public void validateText() {

	}

	public void validateElementPresent() {

	}

	public void validateElementClickable() {

	}

	public void validateElementDisplayed() {

	}
}
