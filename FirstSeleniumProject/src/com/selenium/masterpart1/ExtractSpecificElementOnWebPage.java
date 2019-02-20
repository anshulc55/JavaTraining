package com.selenium.masterpart1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExtractSpecificElementOnWebPage {
	
WebDriver driver = null;
	
	@BeforeMethod
	public void openBrowser(){
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		 driver = new ChromeDriver();

		driver.get("https://edition.cnn.com");
		driver.manage().window().maximize();

		// Apply Implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}
	
	@Test
	public void getElements(){
		
		WebElement topStories = driver.findElement(By.xpath("//*[@id='intl_homepage1-zone-1']/div[2]/div/div[2]"));
		
		List<WebElement> topStoriesLinks = topStories.findElements(By.tagName("a"));
		
		System.out.println("Number of Top Stories on CNN are : " + topStoriesLinks.size());
		
		for (WebElement topLink : topStoriesLinks) {
			 String topLinkText = topLink.getText();
			 
			 if(!topLinkText.isEmpty()){
				 System.out.println(topLinkText);
			 }
			
		}
		
	}

}
