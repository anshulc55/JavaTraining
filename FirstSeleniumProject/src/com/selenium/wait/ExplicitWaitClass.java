package com.selenium.wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExplicitWaitClass {

	WebDriver driver = null;

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/JavaTraining/FirstSeleniumProject/drivers/chromedriver");
		
		driver.manage().window().maximize();

		driver.get("file:///Users/anshul/Downloads/ExplicitWait.html");
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// For Windows Machine
		// File Location : c:\\test\selenium\abcd.html
		// driver.get("file:///C:/Selenium/abcd.html");
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

	@Test
	public void verifyAlert() {

		WebElement alertBtn = driver.findElement(By.xpath("//button[@id='alert']"));
		alertBtn.click();

		// Explicit Wait
		//Selenium 3
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		
		//Selenium 4
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.alertIsPresent());

		driver.switchTo().alert().accept();

	}

	@Test
	public void verifyElementClickable() {

		// First We will perform Action
		// Putwait and call expected condition
		// Perform action on expected Element
		driver.findElement(By.id("display-other-button")).click();

		//WebDriverWait wait = new WebDriverWait(driver, 30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("hidden"))));

	}

	@Test
	public void verifyElementSelected() {

		driver.findElement(By.id("checkbox")).click();

		//WebDriverWait wait = new WebDriverWait(driver, 20);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeSelected(driver.findElement(By.xpath("//input[@type='checkbox']"))));

	}

	@Test
	public void veriyPresentText() {
		driver.findElement(By.id("populate-text")).click();

		//WebDriverWait wait = new WebDriverWait(driver, 30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.textToBePresentInElement(
				driver.findElement(By.xpath("//h2[@class='target-text']")), "Selenium Webdriver"));
	}

	@Test
	public void verifyElementVisibility() {
		driver.findElement(By.id("display-other-button")).click();

		//WebDriverWait wait = new WebDriverWait(driver, 30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hidden")));
	}

}
