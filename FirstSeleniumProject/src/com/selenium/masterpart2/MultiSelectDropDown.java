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

public class MultiSelectDropDown {
	
WebDriver driver = null;
	
	@BeforeMethod
	public void openBrowser(){
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		 driver = new ChromeDriver();

		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");
		driver.manage().window().maximize();

		// Apply Implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}
	
	@Test
	public void selectMultiSelectDropDown(){
		
		driver.switchTo().frame("iframeResult");
		
		WebElement multiSelect = driver.findElement(By.name("cars"));
		
		Select select = new Select(multiSelect);
		
		//isMultiple
	  System.out.println(select.isMultiple());
	  
	  select.selectByIndex(1);
	  select.selectByIndex(3);
	  
	  try {
		Thread.sleep(4000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	    //getFirstSelectedOption() 
		System.out.println(select.getFirstSelectedOption().getText());
		System.out.println("*******************");

		//getAllSelectedOptions() 
		List<WebElement> selectedOptions = select.getAllSelectedOptions();
		for (WebElement selectValue : selectedOptions) {
			System.out.println(selectValue.getText());
		}
		 
		//deselectByIndex(int index) 
		//deselectByValue(String value) 
		//deselectByVisibleText(String text) 
		//deselectAll()
		
		select.deselectAll();
		
		  try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}

}
