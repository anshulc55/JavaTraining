package SeleniumScenario1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExtractTextFromWebElement {

	WebDriver driver = null;
	
	@Test
	public void extractTextfromWebPage() {
		
		//Extracting Text from Heading
		WebElement heading = driver.findElement(By.className("_8eso"));
		//heading.getText();
		Assert.assertEquals(heading.getText(), "Facebook helps you connect and share with the people in your life.");
		
		//Extract Text from Link
		WebElement linkText = driver.findElement(By.className("_8esh"));
		Assert.assertEquals(linkText.getText(), "Create a Page");
		
		//Extract Text from PlaceHolder Textbox
		WebElement placeholderText = driver.findElement(By.id("email"));
		String placeholderValue = placeholderText.getAttribute("aria-label");
		Assert.assertEquals(placeholderValue, "Email address or phone number");
		
	}

	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.get("https://en-gb.facebook.com/");
	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
