package com.selenium.masterpart2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectDropDown {
	
WebDriver driver = null;
	
	@BeforeMethod
	public void openBrowser(){
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		 driver = new ChromeDriver();

		driver.get("https://ww.facebook.com");
		driver.manage().window().maximize();

		// Apply Implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}
	
	@Test
	public void SelectDOB(){
		
		WebElement monthDropDown = driver.findElement(By.name("birthday_month"));
	
		//selectByIndex(int index) 
		Select select = new Select(monthDropDown);
		select.selectByIndex(5);
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//selectByValue(String value) 
		select.selectByValue("11");
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//selectByVisibleText(String text) 
		select.selectByVisibleText("Sept");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//getOptions() 
		List<WebElement>  monthValues = select.getOptions();
		for (WebElement month : monthValues) {
			System.out.println(month.getText());
		}
	}
	
	 
	
	
	
	//getFirstSelectedOption() 
	//getAllSelectedOptions() 
	//isMultiple() 
	//deselectByIndex(int index) 
	//deselectByValue(String value) 
	//deselectByVisibleText(String text) 
	//deselectAll()

}
