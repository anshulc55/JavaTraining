package com.selenium.webelement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class XpathClass {

	@Test
	public void absoluteXpath() {

		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.rediff.com/");

		driver.findElement(By.linkText("Sign in")).click();

		driver.findElement(By.xpath("//input[@id='login1]")).sendKeys("anshulc55@gmail.com");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// driver.findElement(By.xpath("/html/body/div/div/div/a/img")).click();
		driver.findElement(By.xpath("//div/a/img")).click();

		Assert.assertEquals(driver.getTitle(),
				"Rediff.com: Online Shopping, Rediffmail, Latest India News, Business, Bollywood, Sports, Stock, Live Cricket Score, Money, Movie Reviews");

		driver.quit();
	}

}
