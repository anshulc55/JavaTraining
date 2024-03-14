package seleniumwaits;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SeleniumImplicitWaits {
	
	@Test
	public void googleSearchTest() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Implicit wait declaration
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Open Google Home Page
		driver.get("https://www.google.com/");
		
		//Type in Search box
		WebElement searchBox = driver.findElement(By.xpath("//textarea[@title='Search' and @role='combobox']"));
		searchBox.sendKeys("Selenium Webdriver");
		
		// Click third search result
		WebElement searchResult = driver.findElement(By.xpath("//li[@role='presentation'][3]"));
		searchResult.click();
		
		driver.quit();
		
	}

}
