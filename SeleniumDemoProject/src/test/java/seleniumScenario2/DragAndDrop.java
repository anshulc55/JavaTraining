package seleniumScenario2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragAndDrop {
	WebDriver driver = null;
	
	@Test
	public void dragAndDropHardenTest() throws InterruptedException {
		Assert.assertEquals(driver.getTitle(), "jQuery UI");
		
		driver.findElement(By.linkText("Droppable")).click();
		Assert.assertEquals(driver.getTitle(), "Droppable | jQuery UI");
		
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		Thread.sleep(5000);
		
		WebElement dragableEle = driver.findElement(By.id("draggable"));
		WebElement dropableEle = driver.findElement(By.id("droppable"));
		
		Actions action = new Actions(driver);
		action.clickAndHold(dragableEle).moveToElement(dropableEle).release().build().perform();
		
		Thread.sleep(5000);
	}
	
	//@Test
	public void dragAndDropTest() throws InterruptedException {
		Assert.assertEquals(driver.getTitle(), "jQuery UI");
		
		driver.findElement(By.linkText("Droppable")).click();
		Assert.assertEquals(driver.getTitle(), "Droppable | jQuery UI");
		
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		Thread.sleep(5000);
		
		WebElement dragableEle = driver.findElement(By.id("draggable"));
		WebElement dropableEle = driver.findElement(By.id("droppable"));
		
		Actions action = new Actions(driver);
		action.dragAndDrop(dragableEle, dropableEle).build().perform();
		
		Thread.sleep(5000);
	}
	
	
	//@Test
	public void dragableTest() throws InterruptedException {
		Assert.assertEquals(driver.getTitle(), "jQuery UI");
		
		driver.findElement(By.linkText("Draggable")).click();
		Assert.assertEquals(driver.getTitle(), "Draggable | jQuery UI");
		
		driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
		Thread.sleep(5000);
		
		WebElement dragableEle = driver.findElement(By.id("draggable"));
		
		Actions action = new Actions(driver);
		action.dragAndDropBy(dragableEle, 200, 200).build().perform();
		
		Thread.sleep(5000);
	}
	
	
	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.get("https://jqueryui.com");

	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
