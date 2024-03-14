package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumMultipleBrowsers {
	
/*
 * Test Case : Open the Facebook and verify Home Page Title
 * Execute Test in Multiple browser - Chrome, Firefox, Opera, Safari, IE, Edge ...
 * 
 * Windows - Task Manager --> ctl+ Shift + Esc
 * Mac - Terminal
 */

	@Test
	public void openInChrome() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");
		WebDriver chromeDriver = new ChromeDriver();
		
		//Maximize Browser Window
		chromeDriver.manage().window().maximize();
		
		//Open FaceBook WebPage
		chromeDriver.get("https://www.facebook.com/");
		
		//Verify the Title
		String title = chromeDriver.getTitle();
		Assert.assertEquals(title, "Facebook – log in or sign up");
		
		Thread.sleep(3000);
		chromeDriver.quit();
	}
	
	@Test
	public void openInFirefox() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/anshul/java_selenium/SeleniumDemoProject/drivers/geckodriver");
		WebDriver driver = new FirefoxDriver();
		
		//Maximize Browser Window
		driver.manage().window().maximize();
		
		//Open FaceBook WebPage
		driver.get("https://www.facebook.com/");
		
		//Verify the Title
		String title = driver.getTitle();
		Assert.assertEquals(title, "Facebook – log in or sign up");
		
		Thread.sleep(3000);
		driver.quit();
	}
	
	/*
	@Test
	public void openInOpera() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/anshul/java_selenium/SeleniumDemoProject/drivers/operadriver");
		WebDriver driver = new ChromeDriver();
		
		//Maximize Browser Window
		driver.manage().window().maximize();
		
		//Open FaceBook WebPage
		driver.get("https://www.facebook.com/");
		
		//Verify the Title
		String title = driver.getTitle();
		Assert.assertEquals(title, "Facebook – log in or sign up");
		
		Thread.sleep(3000);
		driver.quit();
	}
	*/
	
	@Test
	public void openInSafari() throws InterruptedException {
		WebDriver driver = new SafariDriver();
		
		//Maximize Browser Window
		driver.manage().window().maximize();
		
		//Open FaceBook WebPage
		driver.get("https://www.facebook.com/");
		
		//Verify the Title
		String title = driver.getTitle();
		//Assert.assertEquals(title, "Facebook – log in or sign up");
		
		Thread.sleep(3000);
		driver.quit();
	}
	
	/*
	@Test
	public void openInIE() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/anshul/java_selenium/SeleniumDemoProject/drivers/IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		
		//Maximize Browser Window
		driver.manage().window().maximize();
		
		//Open FaceBook WebPage
		driver.get("https://www.facebook.com/");
		
		//Verify the Title
		String title = driver.getTitle();
		Assert.assertEquals(title, "Facebook – log in or sign up");
		
		Thread.sleep(3000);
		driver.quit();
	}
	*/
	
	@Test
	public void openInEdge() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/anshul/java_selenium/SeleniumDemoProject/drivers/msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		
		//Maximize Browser Window
		driver.manage().window().maximize();
		
		//Open FaceBook WebPage
		driver.get("https://www.facebook.com/");
		
		//Verify the Title
		String title = driver.getTitle();
		//Assert.assertEquals(title, "Facebook – log in or sign up");
		
		Thread.sleep(3000);
		driver.quit();
	}
	
}
