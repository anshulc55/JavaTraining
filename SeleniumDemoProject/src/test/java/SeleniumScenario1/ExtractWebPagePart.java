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

public class ExtractWebPagePart {
	WebDriver driver = null;

	@Test
	public void extractElementTest() {
		WebElement topStories = driver.findElement(By.xpath(
				"//*[@class='container container_lead-plus-headlines cnn lazy' and @data-collapsed-text='More top stories' ]"));
		Assert.assertTrue(topStories.isDisplayed());
		System.out.println("Is Top Stories Available : " + topStories.isDisplayed());
		
		List<WebElement> topStory = topStories.findElements(By.tagName("a"));
		System.out.println("Total Top Stories : " + topStory.size());
		
		for (WebElement story : topStory) {
			if(!story.getText().isEmpty()) {
				System.out.println(story.getText());
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

		// driver.get("https://edition.cnn.com/");
		driver.get("https://edition.cnn.com");
	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
