package SeleniumIntro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class FirstSeleniumTest {
	
	@Test
	public void OpenSiteInChrome() {
		
		// Set the path to the ChromeDriver executable
		System.setProperty("webdriver.chrome.driver", "/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");
		
		// Create a new instance of the ChromeDriver
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("https://www.facebook.com");
		driver.quit();
		
	}
	
	@Test
	public void OpenSiteInFirefox() {
		
		// Set the path to the ChromeDriver executable
		System.setProperty("webdriver.gecko.driver", "/Users/anshul/java_selenium/SeleniumDemoProject/drivers/geckodriver");
		
		// Create a new instance of the ChromeDriver
		WebDriver driver = new FirefoxDriver();
		
		driver.get("https://www.cnn.com");
		driver.quit();
		
	}

}
