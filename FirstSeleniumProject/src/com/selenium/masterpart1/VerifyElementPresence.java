package com.selenium.masterpart1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyElementPresence {
	
	WebDriver driver = null;
	
	@BeforeMethod
	public void openBrowser(){
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		 driver = new ChromeDriver();

		driver.get("https://jqueryui.com");
		driver.manage().window().maximize();

		// Apply Implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}
	
	@Test
	public void verifyElementIsDisplayed(){
		
		driver.findElement(By.linkText("Toggle")).click();
		WebElement displayBox =driver.findElement(By.xpath("//*[@id='effect']/h3"));
		
		Assert.assertTrue(displayBox.isDisplayed());
		System.out.println("Box is displaying on webpage");
		
		driver.findElement(By.xpath("//*[contains(@class,'ui-state-default')]")).click();
		
		if(displayBox.isDisplayed()){
			System.out.println("Box is still displayed");
		}else{
			System.out.println("This is expected, Box is being hide on webpage");
		}
		
		
	}

}
