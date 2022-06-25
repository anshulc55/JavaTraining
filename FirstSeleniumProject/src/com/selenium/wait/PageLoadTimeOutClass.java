package com.selenium.wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class PageLoadTimeOutClass {
	
	@Test
	public void pageLoadTimeOutTest(){
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul//JavaTraining/FirstSeleniumProject/drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Define Page Load TimeOut
		
		//Selenium 3
		//driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		
		// Selenium 4
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(2));
		
		driver.get("https://edition.cnn.com/");
		
		driver.quit();
		
	}

}
