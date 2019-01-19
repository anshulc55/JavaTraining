package com.selenium.webelement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FindElementsClass {
	
	@Test
	public void findElementTest() {
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.freecrm.com/index.html");
		
		List<WebElement> textFields = driver.findElements(By.className("form-control"));
		
		textFields.get(0).sendKeys("Anshulc55@gmail.com");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textFields.get(1).sendKeys("Test@1234");
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.quit();

	}

}
