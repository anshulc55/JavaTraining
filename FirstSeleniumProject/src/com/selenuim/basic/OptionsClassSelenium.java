package com.selenuim.basic;

import java.io.File;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OptionsClassSelenium {

	public void sampleOptionsSetBinary() {

		/*
		 * //open Specific Version of Chrome
		 * System.setProperty("webdriver.chrome.driver",
		 * "D:\\PATH\\chromedriver.exe"); ChromeOptions co = new
		 * ChromeOptions(); // set the binary path co.setBinary(
		 * "C:\\Program Files (x86)\\Google\\Chrome\\Application\\version45\\chrome.exe"
		 * ); //open the browser WebDriver driver = new ChromeDriver(co);
		 * driver.get("https://google.com");
		 */

		// Open Custom FireFox Version
		System.setProperty("webdriver.gecko.driver", "D:\\PATH\\geckodriver.exe");
		FirefoxOptions fo = new FirefoxOptions();
		fo.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe")));
		// open the browser
		WebDriver driver = new FirefoxDriver();
		driver.get("https://bing.com");

	}

	public void acceptCertificate() {

		System.setProperty("webdriver.gecko.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/geckodriver");

		FirefoxOptions fo = new FirefoxOptions();
		fo.setAcceptInsecureCerts(false);

		WebDriver driver = new FirefoxDriver(fo);
		driver.manage().window().maximize();

		driver.get("https://cacert.org");

	}

	public void setProxy() {

		System.setProperty("webdriver.gecko.driver", "D:\\PATH\\geckodriver.exe");
		FirefoxOptions fo = new FirefoxOptions();
		// Create object Proxy class
		Proxy prox = new Proxy();
		prox.setProxyAutoconfigUrl("https://proxy.chercher.tech");
		// register the proxy with options class
		fo.setProxy(prox);
		// create object to firefx driver
		WebDriver driver = new FirefoxDriver(fo);

	}

	public void OpenHeadLess() {
		System.setProperty("webdriver.gecko.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/geckodriver");

		FirefoxOptions fo = new FirefoxOptions();

		fo.setHeadless(true);

		WebDriver driver = new FirefoxDriver(fo);
		driver.manage().window().maximize();

		driver.get("https://cacert.org");

		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, "Welcome to CAcert.org");

	}

	
	public void setArguments() {

		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--disable-infobars");
		
		WebDriver driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		
		driver.get("https://www.bing.com");

	}
	
	@Test
	public void SetBrowserNotification() {

		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--disable-infobars");
		co.addArguments("--disable-notifications");
		
		WebDriver driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		
		driver.get("https://drupal-stage-web.weather.com/en-IN");

	}
	
	@Test
	public void handleUnHandledAlert() {

		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		
		ChromeOptions co = new ChromeOptions();
		co.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		
		WebDriver driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		
		driver.get("https://drupal-stage-web.weather.com/en-IN");

	}

	
	@Test
	public void SetPageLoadStrategy() {

		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		
		ChromeOptions co = new ChromeOptions();
		co.setPageLoadStrategy(PageLoadStrategy.EAGER);
		
		WebDriver driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		
		driver.get("https://drupal-stage-web.weather.com/en-IN");

	}

}
