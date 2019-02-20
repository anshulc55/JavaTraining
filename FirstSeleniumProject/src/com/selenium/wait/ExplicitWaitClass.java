package com.selenium.wait;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExplicitWaitClass {

	WebDriver driver = null;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("file:///Users/anshul/Trainings/Selenium/Waits%20in%20Selenium/ExplicitWait.html");
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// For Windows Machine
		// File Location : c:\\test\selenium\abcd.html
		// driver.get("file:///C:/Selenium/abcd.html");
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

	@Test
	public void verifyAlert() {

		WebElement alertBtn = driver.findElement(By.xpath("//button[@id='alert']"));
		alertBtn.click();

		// Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());

		driver.switchTo().alert().accept();

	}

	@Test
	public void verifyElementClickable() {

		// First We will perform Action
		// Putwait and call expected condition
		// Perform action on expected Element
		driver.findElement(By.id("display-other-button")).click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("hidden"))));

	}

	@Test
	public void verifyElementSelected() {

		driver.findElement(By.id("checkbox")).click();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeSelected(driver.findElement(By.xpath("//input[@type='checkbox']"))));

	}

	@Test
	public void veriyPresentText() {
		driver.findElement(By.id("populate-text")).click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElement(
				driver.findElement(By.xpath("//h2[@class='target-text']")), "Selenium Webdriver"));
	}

	@Test
	public void verifyElementVisibility() {
		driver.findElement(By.id("display-other-button")).click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hidden")));
	}

}
