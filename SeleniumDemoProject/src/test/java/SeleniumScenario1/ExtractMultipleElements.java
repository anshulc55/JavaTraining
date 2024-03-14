package SeleniumScenario1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExtractMultipleElements {

	WebDriver driver = null;

	@Test
	public void findNumberOfLinksOnWebPageTest() {

		// Total Number of Links
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		System.out.println("Number of Links :: " + allLinks.size());

		// Extract text and URL of Specific Link - 6
		WebElement sixthLink = allLinks.get(5);
		System.out.println("6th Link Text :: " + sixthLink.getText());
		System.out.println("6th Link URL :: " + sixthLink.getAttribute("href"));

		// Extract text and URL of everyLink
		System.out.println("************************************************");
		int counter = 1;
		for (WebElement link : allLinks) {
			if (link.isDisplayed()) {
				System.out.println(counter + ". Link Text is :: " + link.getText() + " --> Link URL is :: "
						+ link.getAttribute("href"));
				counter++;
			}
		}

	}

	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.get("https://edition.cnn.com/");
	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
