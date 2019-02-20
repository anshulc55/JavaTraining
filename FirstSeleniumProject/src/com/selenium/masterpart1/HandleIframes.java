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

public class HandleIframes {
	
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
	public void getIFrame(){
		
		driver.findElement(By.linkText("Button")).click();
		
		WebElement pageTitle = driver.findElement(By.className("entry-title"));
		Assert.assertEquals(pageTitle.getText(), "Button");
		
		
		//Working inside the Frame
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
		WebElement frstBtn = driver.findElement(By.xpath("//*[@class='widget']/button"));
		Assert.assertEquals(frstBtn.getText(), "A button element");
		
		//Keep Selenium Focus on main Page
		driver.switchTo().parentFrame();
		
		WebElement exampleElement = driver.findElement(By.xpath("//*[@id='content']/div[1]/h2"));
		Assert.assertEquals(exampleElement.getText(), "Examples");
		
		
	}

}
