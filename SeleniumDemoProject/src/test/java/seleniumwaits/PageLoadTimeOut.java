package seleniumwaits;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PageLoadTimeOut {
	
	@Test
	public void pageLoadTest() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Page Load Time
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
		
		//Open Google Home Page
		driver.get("https://edition.cnn.com/");
		
		Assert.assertEquals(driver.getTitle(), "Breaking News, Latest News and Videos | CNN");
		driver.quit();
	}

}
