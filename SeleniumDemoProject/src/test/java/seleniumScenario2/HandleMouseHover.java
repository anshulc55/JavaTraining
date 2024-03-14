package seleniumScenario2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandleMouseHover {
	WebDriver driver = null;
	
	@Test
	public void performMouseHoverTest() throws InterruptedException {
		Assert.assertEquals(driver.getTitle(), "American Golf · Europe's Largest Golf Retailer · Online & Instore");
		
		//Perform Mouse Hover
		List<WebElement> menu = driver.findElements(By.xpath("//*[@class='a-level-1']"));
		int counter = 0;
		for (WebElement link : menu) {
			System.out.println("Link Number : " + counter + " Menu Tittle : " + link.getText());
			counter ++;
		}
		Thread.sleep(5000);
		
		WebElement waterProof = menu.get(2);
		WebElement golfClubs = menu.get(3);
		WebElement golfClothing = menu.get(4);
		WebElement golfShoes = menu.get(5);
		
		Actions action = new Actions(driver);
		action.moveToElement(waterProof).build().perform();
		//Thread.sleep(5000);
		
		action.moveToElement(golfClubs).build().perform();
		//Thread.sleep(5000);
		
		action.moveToElement(golfClothing).build().perform();
		//Thread.sleep(5000);
		
		action.moveToElement(golfShoes).build().perform();
		//Thread.sleep(5000);
		
		driver.findElement(By.xpath("//*[@id=\"GOLF-SHOES_1\"]/ul/li[4]/ul/li[5]/a")).click();
		
		Assert.assertEquals(driver.getTitle(), "adidas Golf Shoes | adidas Boost & Adicross Golf Shoes | American Golf");
		//Thread.sleep(5000);
	}
	
	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.get("https://www.americangolf.eu");

	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
