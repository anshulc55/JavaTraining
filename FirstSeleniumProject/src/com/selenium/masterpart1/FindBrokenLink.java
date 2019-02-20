package com.selenium.masterpart1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindBrokenLink {

	@Test
	public void testimplicitWait() {

		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		WebDriver driver = new ChromeDriver();

		driver.get("https://edition.cnn.com");
		driver.manage().window().maximize();

		// Apply Implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		List<WebElement> links = driver.findElements(By.tagName("a"));

		System.out.println("Number of Links are : " + links.size());

		for (WebElement link : links) {
			String URL = link.getAttribute("href");
			GetLinkStatus.verifyLink(URL);
		}
		
		System.out.println("Total Number of Broken Links :");
		GetLinkStatus.getInvalidLinkCount();
		
		driver.quit();
	}

}
