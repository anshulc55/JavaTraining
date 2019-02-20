package com.selenium.masterpart1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtractTextInSelenium {
	
	@Test
	public void testimplicitWait(){
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.facebook.com");
		driver.manage().window().maximize();
		
		//Apply Implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String headingtext = driver.findElement(By.xpath("//*[contains(@class,'mbs _52lq')]/span")).getText();
		System.out.println(headingtext);
		Assert.assertEquals(headingtext, "Create an account");
		
		String signUp = driver.findElement(By.name("websubmit")).getText();
		System.out.println(signUp);
		Assert.assertEquals(signUp, "Sign Up");
		
		String placeHolder = driver.findElement(By.xpath("//*[@name='reg_email__']")).getAttribute("aria-label");
		System.out.println(placeHolder);
		Assert.assertEquals(placeHolder, "Mobile number or email address");
	
		
		driver.quit();
		
	}

}
