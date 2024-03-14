package seleniumScenario2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandleDropDown {
	WebDriver driver = null;

	@Test
	public void dropDownMultiSelectTest() throws InterruptedException {
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");

		driver.switchTo().frame("iframeResult");

		WebElement dropDown = driver.findElement(By.id("cars"));
		Select multiSelect = new Select(dropDown);

		Thread.sleep(5000);

		// Verify is Dropdown MultiSelect
		Assert.assertTrue(multiSelect.isMultiple());

		// Select any Element
		multiSelect.selectByValue("volvo");
		Thread.sleep(5000);
		multiSelect.selectByValue("opel");
		Thread.sleep(5000);

		// Verify Selected Options
		List<WebElement> selectedOptions = multiSelect.getAllSelectedOptions();
		for (WebElement options : selectedOptions) {
			System.out.println("Selected Values : " + options.getText());
		}

		// DeSelect All
		multiSelect.deselectAll();
		Thread.sleep(5000);

		// Select Again
		multiSelect.selectByValue("saab");
		Thread.sleep(5000);
		multiSelect.selectByValue("audi");
		Thread.sleep(5000);

		// Select first Element
		String value = multiSelect.getFirstSelectedOption().getText();
		System.out.println("First Selected Text is : " + value);

		multiSelect.deselectByVisibleText("Audi");
	}

	
	// @Test
	public void useDropDownTest() throws InterruptedException {
		driver.get("https://en-gb.facebook.com");
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

		// Click Create Account Button
		WebElement createAccountBtn = driver.findElement(By.xpath("//*[@data-testid='open-registration-form-button']"));
		createAccountBtn.click();

		// Work with Month DropDown
		WebElement monthDropDown = driver.findElement(By.id("month"));
		Select dropdown = new Select(monthDropDown);

		Thread.sleep(5000);

		// Select By Index
		dropdown.selectByIndex(1);
		Thread.sleep(5000);

		// Select by Value
		dropdown.selectByValue("5");
		Thread.sleep(5000);

		// Select by Visible Text
		dropdown.selectByVisibleText("Jul");
		Thread.sleep(5000);

		List<WebElement> allOptions = dropdown.getOptions();
		for (WebElement option : allOptions) {
			option.click();
			System.out.println("Option Selected : " + option.getText());
			Thread.sleep(2000);
		}

	}

	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		// driver.get("https://edition.cnn.com/");

	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
