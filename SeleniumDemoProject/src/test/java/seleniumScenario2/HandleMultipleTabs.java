package seleniumScenario2;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandleMultipleTabs {
	WebDriver driver = null;
	
	@Test
	public void getmultipleWindowHandleTest() throws InterruptedException {
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
		driver.findElement(By.id("email")).sendKeys("anshulc55@icloud.com");
		Thread.sleep(3000);
		
		//Browser Window GUID
		String mainPageID = driver.getWindowHandle();
		System.out.println("Main Page Window ID : " + mainPageID);
		
		driver.findElement(By.linkText("Meta Pay")).click();
		
		Set<String> windowIds = driver.getWindowHandles();
		Iterator<String> itr = windowIds.iterator();
		
		String homePageID = itr.next();
		String metaPayPageID = itr.next();
		
		driver.switchTo().window(metaPayPageID);
		
		Assert.assertEquals(driver.getTitle(), "Meta Pay: Simple, Secure, Free Payments");
		String bannerText = driver.findElement(By.className("banner__content-cell")).getText();
		System.out.println(bannerText);
		
		driver.close();
		Thread.sleep(5000);
		
		driver.switchTo().window(homePageID);
		
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
		driver.findElement(By.id("email")).sendKeys("anshulc55@icloud.com");
		Thread.sleep(3000);
		
	}
	
	
	//@Test
	public void getWindowIDTest() throws InterruptedException {
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
		driver.findElement(By.id("email")).sendKeys("anshulc55@icloud.com");
		Thread.sleep(3000);
		
		//Browser Window GUID
		String mainPageID = driver.getWindowHandle();
		System.out.println("Main Page Window ID : " + mainPageID);
		
		//Click Link which Open in Same Browser Tab
		driver.findElement(By.linkText("Forgotten password?")).click();
		
		driver.findElement(By.id("identify_email")).sendKeys("testemail123@gmail.com");
		Thread.sleep(3000);
		
		//Browser Window GUID
		String forfotPasswordPageID = driver.getWindowHandle();
		System.out.println("Forgotten Password Page Window ID : " + forfotPasswordPageID);
		
	}
	
	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.get("https://en-gb.facebook.com");

	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
