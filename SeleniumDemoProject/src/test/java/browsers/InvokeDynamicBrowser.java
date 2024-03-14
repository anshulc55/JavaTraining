package browsers;

import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import org.testng.AssertJUnit;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class InvokeDynamicBrowser {

	/*
	 * Test Case : Open the selenium official site and verify Home Page Title
	 * 
	 * Test Case 2 : Open the Yahoo and verify Home Page Title
	 * 
	 */

	public WebDriver driver = null;

	@Parameters("browser")
	@BeforeMethod
	public void openBrowser(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized", "--disable-infobars");
			options.addArguments("--disable-extensions");
			
			// Use proxy
			/*
			Proxy proxy = new Proxy();
			proxy.setHttpProxy("proxy.example.com:8080");
			options.setCapability("proxy", proxy);
			*/
			
			options.addArguments("--incognito");
			options.addArguments("--disable-notifications");
			options.addArguments("ignore-certificate-errors");
			
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.chrome.driver",
					"/Users/anshul/java_selenium/SeleniumDemoProject/drivers/geckodriver");
			FirefoxOptions options  = new FirefoxOptions();
			
			ProfilesIni profiles = new ProfilesIni();
			FirefoxProfile ffprofile = profiles.getProfile("TestUser");
			
			ffprofile.setPreference("dom.webnotifications.enabled", false);
			ffprofile.setAcceptUntrustedCertificates(true);
			ffprofile.setAssumeUntrustedCertificateIssuer(false);

			
			// Use proxy
			/*
			ffprofile.setPreference("network.proxy.type", 1);
			ffprofile.setPreference("network.proxy.socks", "proxy.example.com");
			ffprofile.setPreference("network.proxy.socks_port", 8080);
			*/
			
			options.setProfile(ffprofile);
			driver = new FirefoxDriver(options);
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.chrome.driver",
					"/Users/anshul/java_selenium/SeleniumDemoProject/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			System.setProperty("webdriver.chrome.driver",
					"/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");
			driver = new ChromeDriver();
		}

		// Maximize Browser Window
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}
	
	@Test
	public void verifyCACertError() throws InterruptedException {
		driver.get("https://expired.badssl.com");
		Thread.sleep(30000);
	}

	//@Test
	public void verifySeleniumSite() throws InterruptedException {

		// Open FaceBook WebPage
		driver.get("https://www.selenium.dev");

		// Verify the Title
		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "Selenium");

		Thread.sleep(3000);
	}

	//@Test
	public void verifyYahooSite() throws InterruptedException {

		// Open FaceBook WebPage
		driver.get("https://mvnrepository.com/repos/central");

		// Verify the Title
		String title = driver.getTitle();
		AssertJUnit.assertEquals(title, "Maven Repository: Central");

		Thread.sleep(3000);
	}

}
