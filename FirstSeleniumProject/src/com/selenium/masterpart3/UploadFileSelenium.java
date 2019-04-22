package com.selenium.masterpart3;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UploadFileSelenium {
	
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
	
	@Test
	public void testUploadFunction(){
		
		driver.get("http://the-internet.herokuapp.com/upload");
		
		WebElement uplaodBtn = driver.findElement(By.id("file-upload"));
		String filePath = "/Users/anshul/Downloads/download.png";
		
		
		
		uplaodBtn.sendKeys(filePath);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("file-submit")).click();
		
		String confirmationTxt = driver.findElement(By.xpath("//*[@id='content']/div/h3")).getText();
		Assert.assertEquals(confirmationTxt, "File Uploaded!");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
