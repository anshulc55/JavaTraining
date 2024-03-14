package SeleniumScenario1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandleIFrames {
	WebDriver driver = null;
	
	@Test
	public void handlIFrameTest() throws InterruptedException {
		
		Assert.assertEquals(driver.getTitle(), "jQuery UI");
		
		//Open Radio button Link
		driver.findElement(By.linkText("Checkboxradio")).click();
		Assert.assertEquals(driver.getTitle(), "Checkboxradio | jQuery UI");
		
		//Verify Page Heading
		String heading =driver.findElement(By.className("entry-title")).getText();
		Assert.assertEquals(heading, "Checkboxradio");
		
		//Switch to Iframe before Interact with iframe Elements
		WebElement iframe = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(iframe);
		
		//Click Radio Button
		List<WebElement> radioBtns = driver.findElements(By.xpath("//span[@class='ui-checkboxradio-icon ui-corner-all ui-icon ui-icon-background ui-icon-blank']"));
		for (WebElement radiobtn : radioBtns) {
			radiobtn.click();
		}
		
		//Switch back to main Page
		driver.switchTo().parentFrame();
		
		driver.findElement(By.linkText("Button")).click();
		Assert.assertEquals(driver.getTitle(), "Button | jQuery UI");
		Thread.sleep(2000);
	}
	
	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		//driver.get("https://edition.cnn.com/");
		driver.get("https://jqueryui.com");
	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
