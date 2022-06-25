package com.selenium.masterpart2;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandleAlerts {
	
WebDriver driver = null;
	
	@BeforeMethod
	public void openBrowser(){
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/JavaTraining/FirstSeleniumProject/drivers/chromedriver");
		 driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Apply Implicit wait
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		
	}
	
	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}
	
	@Test
	public void handleJavaScriptAlerts(){
		
		driver.get("https://www.rediff.com/");
		
		driver.findElement(By.xpath("//*[@id=\"signin_info\"]/a[1]")).click();
		
		WebElement username = driver.findElement(By.id("login1"));
		username.sendKeys("anshulc55@gmail.com");
		
		WebElement singinBtn = driver.findElement(By.name("proceed"));
		singinBtn.click();
		
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alt = driver.switchTo().alert();
		System.out.println(alt.getText());
		
		alt.accept();
		
		driver.findElement(By.id("password")).sendKeys("Test@1234");
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
