package com.selenium.webelement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CNNTest {
	
	@Test
	public void testTravelLink(){
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://edition.cnn.com/");
		Assert.assertEquals(driver.getTitle(), "CNN International - Breaking News, US News, World News and Video");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[2]/div[2]/a[6]")).click();
		
		driver.findElement(By.xpath("//*[@id='nav']/div[2]/div[2]/a[6]")).click();
		Assert.assertEquals(driver.getTitle(), "CNN Travel | Global Destinations, Tips & Video");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.quit();
		
		
	}

}
