package com.selenium.masterpart2;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandleMultipleWindows {
	
	WebDriver driver = null;
	
	@BeforeMethod
	public void openBrowser(){
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		 driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Apply Implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}
	
	//@Test
	public void handleFacebook()
	{
		
		driver.get("https://www.facebook.com");
		String mainPageWindow = driver.getWindowHandle();
		System.out.println("Main Page ID : " +mainPageWindow);
		
		driver.findElement(By.linkText("Forgotten account?")).click();
		String forgotAccountPage = driver.getWindowHandle();
		System.out.println("Forgot Account Page ID : " + forgotAccountPage);
	}
	
	@Test
	public void handleMultiTabs(){
		
		driver.get("https://www.jobserve.com");
		String mainPageWindow = driver.getWindowHandle();
		System.out.println("Main Page ID of JobServe: " +mainPageWindow);
		
		driver.findElement(By.id("SPTPosTitleLink")).click();
		//String CurrentWidow = driver.getWindowHandle();
		//System.out.println("Current Page ID : " + CurrentWidow);
		
		Set<String> windowIDs = driver.getWindowHandles();
		Iterator<String> itr = windowIDs.iterator();
		
		String mainPageID = itr.next();
		String jobPageID = itr.next();
		
		//Switching to new Tab/Window
		driver.switchTo().window(jobPageID);
		
		String JobHeading = driver.findElement(By.id("md_visareq")).getText();
		System.out.println(JobHeading);
		
		driver.close();
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.switchTo().window(mainPageID);
		
		driver.findElement(By.id("txtKey")).sendKeys("Selenium Automation Tester");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
