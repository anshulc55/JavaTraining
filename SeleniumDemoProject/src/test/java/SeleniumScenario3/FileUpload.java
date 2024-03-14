package SeleniumScenario3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FileUpload {
	WebDriver driver = null;
	
	@Test
	public void uplaodFileTest() throws InterruptedException {
		Assert.assertEquals(driver.getTitle(), "The Internet");
		
		//Upload the file
		WebElement fileUploadBtn = driver.findElement(By.id("file-upload"));
		String fileName = "/Users/anshul/Downloads/1685014961151.jpeg";
		
		fileUploadBtn.sendKeys(fileName);
		Thread.sleep(5000);
		
		WebElement fileSubmitBtn = driver.findElement(By.id("file-submit"));
		fileSubmitBtn.click();
		Thread.sleep(5000);
		
		WebElement fileConfirmation = driver.findElement(By.className("example"));
		Assert.assertEquals(fileConfirmation.getText(), "File Uploaded!");

	}
	
	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.get("https://the-internet.herokuapp.com/upload");

	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
