package com.selenuim.basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvokeOtherBrowsers {
	
	@Test
	public void verifyFaceBookHomePage(){
		
		String URL = "https://www.facebook.com";
		
/*//		//Open the URL with Selenium
		System.setProperty("webdriver.chrome.driver", "/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		
//		//Only for Windpws
//		//System.setProperty("webdriver.chrome.driver", "c:\\test\\selenium.....\\chromedriver.exe")
		
		WebDriver driver = new ChromeDriver();*/
		
		//FireFox Browser Invoke
		System.setProperty("webdriver.gecko.driver", "/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/geckodriver");
		WebDriver driver = new FirefoxDriver();
		
		/*System.setProperty("webdriver.opera.driver", "/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/operadriver");
		WebDriver driver = new OperaDriver();*/
		
	/*	System.setProperty("webdriver.ie.driver", "PATH IE Driver EXE");
		WebDriver driver = new InternetExplorerDriver();*/
		
		//To maximize the Browser
		driver.manage().window().maximize();
				
		driver.get(URL);
		
		//Verify HomePage Tiitle
		String pageTitle = driver.getTitle();
		System.out.println("We get the Title Like :" +pageTitle);
		
		Assert.assertEquals(pageTitle, "Facebook – log in or sign up");
		
		//To Close the Browser
		driver.quit();
		
	}

}
