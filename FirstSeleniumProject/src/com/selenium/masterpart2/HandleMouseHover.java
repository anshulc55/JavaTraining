package com.selenium.masterpart2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandleMouseHover {
	
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
	public void handleMouseHoverOnClick(){
		
		driver.get("https://www.americangolf.co.uk/");
		WebElement clothLink = driver.findElement(By.xpath("//*[@id='header-navigation']/div[1]/ul/li[3]/a"));
		
		Actions action = new Actions(driver);
		action.moveToElement(clothLink).build().perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='CLOTHFOOTW_1']/ul/li[3]/ul/li[1]/a/span"))));
		
		WebElement trouserLink = driver.findElement(By.xpath("//*[@id='CLOTHFOOTW_1']/ul/li[3]/ul/li[1]/a/span"));
		trouserLink.click();
		
		
		
		Assert.assertEquals("Golf Trousers - Golf Clothing - american golf", driver.getTitle());
				
	}

}
