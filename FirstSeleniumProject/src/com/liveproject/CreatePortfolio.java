package com.liveproject;

import java.util.concurrent.TimeUnit;

import javax.crypto.SealedObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CreatePortfolio {

	WebDriver driver = null;

	@BeforeSuite
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Apply Implicit wait
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);

	}

	/*
	 * @AfterMethod public void closeBrowser() { driver.quit(); }
	 */

	@Test(priority = 1)
	public void testCreatePortfolio() {
		// Open the Refiff
		driver.get("https://www.rediff.com/");
		// Click the Money Link
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/a[2]")).click();

		driver.findElement(By.xpath("//*[@id='signin_info']/a[1]")).click();

		driver.findElement(By.xpath("//*[@id='useremail']")).sendKeys("anshulc55@rediffmail.com");
		driver.findElement(By.xpath("//*[@id='emailsubmit']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='userpass']"))));

		driver.findElement(By.xpath("//*[@id='userpass']")).sendKeys("Test@1234");
		driver.findElement(By.xpath("//*[@id='userpass']")).sendKeys(Keys.ENTER);

		wait.until(ExpectedConditions.elementToBeClickable(By.id("createPortfolio")));
		driver.findElement(By.id("createPortfolio")).click();

		driver.findElement(By.id("create")).clear();
		driver.findElement(By.id("create")).sendKeys("AnshulTestPorfolio");

		driver.findElement(By.id("createPortfolioButton")).click();

	}

	@Test(priority = 2)
	public void testportfolioVerification() {

		waitforElement("//*[@id='portfolioid']", "AnshulTestPorfolio");

	}
	
	@Test (priority=3)
	public void testDeletePortfolio(){
		driver.findElement(By.id("deletePortfolio")).click();
		
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
	}

	public void waitforElement(String xpath, String elementValue) {

		int i = 0;
		while (i != 10) {
			WebElement portfolioDropdown = driver.findElement(By.xpath(xpath));

			Select select = new Select(portfolioDropdown);
			String portfolioName = select.getFirstSelectedOption().getText();

			if (portfolioName.equalsIgnoreCase(elementValue)) {
				return;
			} else {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
		}

		Assert.assertTrue(false, "The Given Text " + elementValue + " is not present in Portfolio Dropdown");
	}

}
