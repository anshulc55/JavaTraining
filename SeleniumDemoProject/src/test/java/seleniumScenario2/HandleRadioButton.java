package seleniumScenario2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandleRadioButton {
	WebDriver driver = null;
	
	@Test
	public void verifYRadioButton() throws InterruptedException {
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
		
		//Click Create Account Button
		driver.findElement(By.xpath("//*[@data-testid='open-registration-form-button']")).click();
		
		//Handle Radio Buttons
		List<WebElement> radioButtons = driver.findElements(By.name("sex"));
		for (WebElement button : radioButtons) {
			System.out.println("Is Radio Button Selected : " + button.isSelected());
		}
		
		radioButtons.get(1).click();
		for (WebElement button : radioButtons) {
			System.out.println("Is Radio Button Selected : " + button.isSelected());
		}
		
		for (WebElement button : radioButtons) {
			button.click();
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

		driver.get("https://en-gb.facebook.com");

	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
