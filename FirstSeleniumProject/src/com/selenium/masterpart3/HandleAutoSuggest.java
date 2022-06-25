package com.selenium.masterpart3;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandleAutoSuggest {
	
WebDriver driver = null;
	
	@BeforeMethod
	public void openBrowser(){
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/JavaTraining/FirstSeleniumProject/drivers/chromedriver");
		 driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Apply Implicit wait
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
	}
	
	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}
	
	@Test
	public void testAutoSuggest(){
		
		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("Selenium");
		
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@role='listbox']/li"))));
		
		List<WebElement> autoSuggets = driver.findElements(By.xpath("//*[@role='listbox']/li"));
		System.out.println(autoSuggets.get(3).getText());
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
