package SeleniumScenario3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandleJavaScriptExecutor {
	WebDriver driver = null;
	
	@Test
	public void HandleJSTest() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//Open WebSite
		js.executeScript("window.location='https://www.rediff.com'");
		
		//Verify page Title 
		String pageTitle = (String) js.executeScript("return document.title");
		Assert.assertEquals(pageTitle, "Rediff.com: News | Rediffmail | Stock Quotes | Shopping");
		
//		//Perform Click Operation 
//		WebElement moneyBtn =  driver.findElement(By.className("signin"));
//		js.executeScript("arguments[0].click();", moneyBtn);
//		
//		//Perform Type Operation
//		WebElement textBox =  driver.findElement(By.id("login1"));
//		js.executeScript("arguments[0].value='test@123'", textBox);
		
		Thread.sleep(5000);
		
		//Scroll webpage upto cordinate
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(5000);
		
		//Scroll to WebElement
		WebElement termBtn =  driver.findElement(By.linkText("Terms of use"));
		js.executeScript("arguments[0].scrollIntoView(true)",termBtn);
		
		Thread.sleep(5000);
		
	}
	
	
	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		//driver.get("https://money.rediff.com/index.html");

	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
