package LiveDemoProject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	
	//Common Methods in Base Class
	//Test Class will inherit the BaseClass
	WebDriver driver = null;
	
	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		//driver.get("https://the-internet.herokuapp.com/upload");

	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
