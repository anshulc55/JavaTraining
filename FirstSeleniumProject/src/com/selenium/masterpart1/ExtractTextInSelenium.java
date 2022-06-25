package com.selenium.masterpart1;

import java.time.Duration;
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
				"/Users/anshul/JavaTraining/FirstSeleniumProject/drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.facebook.com");
		driver.manage().window().maximize();
		
		//Apply Implicit wait
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		String headingtext = driver.findElement(By.xpath("//*[@class='_8eso']")).getText();
		System.out.println(headingtext);
		Assert.assertEquals(headingtext, "Facebook helps you connect and share with the people in your life.");
		
//		String signUp = driver.findElement(By.name("websubmit")).getText();
//		System.out.println(signUp);
//		Assert.assertEquals(signUp, "Sign Up");
		
		String placeHolder = driver.findElement(By.xpath("//*[@class='inputtext _55r1 _6luy']")).getAttribute("placeholder");
		System.out.println(placeHolder);
		Assert.assertEquals(placeHolder, "Email address or phone number");
	
		
		driver.quit();
		
	}

}
