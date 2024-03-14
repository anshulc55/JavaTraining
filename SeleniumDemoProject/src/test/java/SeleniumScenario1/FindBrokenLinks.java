package SeleniumScenario1;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FindBrokenLinks {
	
	WebDriver driver = null;
	
	@Test
	public void finBrokenLinksTest() throws IOException {
		
		//All the links on webpage
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		System.out.println("Total Number of Links :: " + allLinks.size());
		
		//Click each Link to verify link Status. 200 - Success, 404 - Broken Link
		for (WebElement link : allLinks) {
			String linkURL = link.getAttribute("href");
			VerifyLinkStatus.verifyLink(linkURL);
		}
		
		System.out.println("Total Number of Invalid Links");
		VerifyLinkStatus.getInvalidLinkCount();
		
	}
	
	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		//driver.get("https://edition.cnn.com/");
		driver.get("https://money.rediff.com/index.html");
	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
