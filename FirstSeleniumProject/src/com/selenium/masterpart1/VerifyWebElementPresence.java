package com.selenium.masterpart1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyWebElementPresence {
	
	WebDriver driver = null;
	
	@BeforeMethod
	public void openBrowser(){
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		 driver = new ChromeDriver();

		driver.get("https://www.facebook.com");
		driver.manage().window().maximize();

		// Apply Implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}
	
	@Test
	public void verifyElementDisplay(){
		
		driver.findElement(By.linkText("Toggle")).click();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
		WebElement toggleHeading = driver.findElement(By.xpath("//*[@id='effect']/h3"));
		
		Assert.assertTrue(toggleHeading.isDisplayed());
		System.out.println("Display status : " + toggleHeading.isDisplayed());
		
	}
	
	@Test
	public void verifyElementisEnabled(){
		WebElement resibleLink = driver.findElement(By.linkText("Resizable"));
		
		System.out.println("Link is Enabled : " + resibleLink.isEnabled());
	}
	
	@Test
	public void verifyElementSelected(){
		
		WebElement radioBtn = driver.findElement(By.xpath("//input[@name='sex' and @value='1']"));
		System.out.println("Female Radio Button Status : " + radioBtn.isSelected());
		Assert.assertFalse(radioBtn.isSelected());
		
		radioBtn.click();
		
		System.out.println("Female Radio Button Status : " + radioBtn.isSelected());
		Assert.assertTrue(radioBtn.isSelected());
		
	}
}
