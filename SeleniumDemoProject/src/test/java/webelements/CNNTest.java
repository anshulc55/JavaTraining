package webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CNNTest {
	
	@Test
	public void testTravelLink(){
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://edition.cnn.com/");
		Assert.assertEquals(driver.getTitle(), "Breaking News, Latest News and Videos | CNN");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[2]/div[2]/a[6]")).click();
		
		driver.findElement(By.xpath("//*[@data-section='world']/a")).click();
		//Assert.assertEquals(driver.getTitle(), "CNN Travel | Global Destinations, Tips & Video");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.quit();
		
		
	}

}
