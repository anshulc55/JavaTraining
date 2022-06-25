package com.selenium.wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ImplicitWaitClass {
	
	
	@Test
	public void testimplicitWait(){
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/JavaTraining/FirstSeleniumProject/drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		
		//Apply Implicit wait
		// Selenium 3
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Selenium 4
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//WebElement of Google Search Box
		WebElement searchBox = driver.findElement(By.xpath("//input[@name='q' and @title='Search']"));
		searchBox.sendKeys("Selenium-webdriver");
		
		//Click the Google Search Result 
		WebElement searchResult = driver.findElement(By.xpath("//li[@role='presentation'][4]"));
		searchResult.click();
	
		driver.quit();
		
	}

}
