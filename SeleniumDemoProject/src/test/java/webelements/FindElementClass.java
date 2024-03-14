package webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FindElementClass {
	@Test
	public void findElementTest() {

		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/java_selenium/SeleniumDemoProject/drivers/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.freecrm.com/index.html");

		driver.findElement(By.className("form-control")).sendKeys("anshulc55@gmail.com");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.findElement(By.className("form-control")).sendKeys("Test@12334");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.findElement(By.className("anshul")).sendKeys("Test");

		driver.quit();

	}
}
