package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserOptions {
	
	//@Test
	public void chromeBrowserProfiling() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		
		// Set Binary -- Setting Chrome Browser Binary Path
		//options.setBinary("path/to/chrome/binary");
		
		// Start Chrome maximized 
		options.addArguments("--start-maximized");
		
		//Specific profile
		// Windows user -- C:\Users\YourUsername\AppData\Local\Google\Chrome\User Data
		// Mac -- /Users/YourUsername/Library/Application Support/Google/Chrome
		options.addArguments("--user-data-dir=/Users/anshul/Library/Application Support/Google/Chrome/Profile 2");
		
		System.setProperty("webdriver.chrome.driver", "/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");
		WebDriver chromeDriver = new ChromeDriver(options);
		
		Thread.sleep(10000);
		//Open FaceBook WebPage
		chromeDriver.get("https://www.facebook.com/");
		
		//Verify the Title
		String title = chromeDriver.getTitle();
		Assert.assertEquals(title, "Facebook – log in or sign up");
		
		chromeDriver.quit();
	}
	
	@Test
	public void firefoxBrowserProfiling() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "/Users/anshul/java_selenium/SeleniumDemoProject/drivers/geckodriver");
		
		FirefoxOptions options = new FirefoxOptions();
		ProfilesIni profiles = new ProfilesIni();
		//String profilePath = "Users/anshul/Library/Caches/Firefox/Profiles/2y7ljcgb.TestUser";
		
		FirefoxProfile ffprofile = profiles.getProfile("TestUser");
		options.setProfile(ffprofile);
		
		WebDriver driver = new FirefoxDriver(options);
		Thread.sleep(10000);
		//Maximize Browser Window
		driver.manage().window().maximize();
		
		//Open FaceBook WebPage
		driver.get("https://www.facebook.com/");
		
		//Verify the Title
		String title = driver.getTitle();
		Assert.assertEquals(title, "Facebook – log in or sign up");
		
		driver.quit();
		
	}
	
	
	public void edgeBrowserProfiling() {
		
	}

}
