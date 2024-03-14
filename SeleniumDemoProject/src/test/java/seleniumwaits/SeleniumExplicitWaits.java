package seleniumwaits;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumExplicitWaits {

	public WebDriver driver = null;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("file:///Users/anshul/Downloads/ExplicitWait.html");
	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

	@Test
	public void verifyAlert() {
		WebElement alertBtn = driver.findElement(By.id("alert"));
		alertBtn.click();
		
		//Explicit Waits
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.alertIsPresent());
		
		//Accept the Alert
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void veryTextPresent() {
		WebElement txtBtn = driver.findElement(By.id("populate-text"));
		txtBtn.click();
		
		WebElement targetTxt = driver.findElement(By.className("target-text"));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.textToBePresentInElement(targetTxt, "Selenium Webdriver"));
		
	}
	
	@Test
	public void verifyButtonAvailbleTest() {
		WebElement Btn = driver.findElement(By.id("display-other-button"));
		Btn.click();
		
		WebElement clickableBtn = driver.findElement(By.id("hidden"));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(clickableBtn));
		
		clickableBtn.click();
	}
	
	
	@Test
	public void verifyElementEnableTest() {
		
		WebElement Btn = driver.findElement(By.id("enable-button"));
		Btn.click();
		
		WebElement clickableBtn = driver.findElement(By.id("disable"));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeClickable(clickableBtn));
		
		clickableBtn.click();
	}
	
	@Test
	public void verifyElementSelectedTest() {
		
		WebElement Btn = driver.findElement(By.id("checkbox"));
		Btn.click();
		
		WebElement checkBox = driver.findElement(By.id("ch"));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		wait.until(ExpectedConditions.elementToBeSelected(checkBox));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
