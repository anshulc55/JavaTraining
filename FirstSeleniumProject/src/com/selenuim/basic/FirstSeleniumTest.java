package com.selenuim.basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstSeleniumTest {
	
	@Test
	public void verifyFaceBookHomePage(){
		
		String URL = "https://www.facebook.com";
		
		//Open the URL with Selenium
		System.setProperty("webdriver.chrome.driver", "/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		
		//Only for Windpws
		//System.setProperty("webdriver.chrome.driver", "c:\\test\\selenium.....\\chromedriver.exe")
		
		WebDriver driver = new ChromeDriver();
		
		//To maximize the Browser
		driver.manage().window().maximize();
				
		driver.get(URL);
		
		//To maximize the Browser
		driver.manage().window().maximize();
		
		//Verify HomePage Tiitle
		String pageTitle = driver.getTitle();
		System.out.println("We get the Title Like :" +pageTitle);
		
		Assert.assertEquals(pageTitle, "Facebook – log in or sign up");
		
		//To Close the Browser
		driver.close();
		
	}

}
