package SeleniumScenario3;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebTable {
	WebDriver driver = null;
	
	@Test
	public void webTableOperationsTest() {
		
		//Verify Page Title
		Assert.assertEquals(driver.getTitle(), "BSE: 74,119.39 | NSE: 22,493.55 - Live Stock Market | Share Prices | Mutual Fund India: Rediff MoneyWiz");
		
		//Open NSE Indices
		driver.findElement(By.linkText("More NSE Indices")).click();
		
		// Open Complete Table
		driver.findElement(By.linkText("Show More >>")).click();
		
		System.out.println("*************Display Number of Rows***************");
		
		// Display Number of Rows 
		List<WebElement> tableRows = driver.findElements(By.xpath("//*[@class='dataTable']/tbody/tr"));
		System.out.println("Total Number of Rows -- " + tableRows.size());
		
		
		System.out.println("*************Get Number of Columns***************");
		
		
		//Get Number of Columns.
		List<WebElement> tableColumns = driver.findElements(By.xpath("//*[@class='dataTable']/thead/tr/th"));
		System.out.println("Total Number of Columns -- " + tableColumns.size());
		
		System.out.println("*************Get Data of a 5th Row***************");
		
		//Get Data of a Specific Row.
		List<WebElement> fifthRow = driver.findElements(By.xpath("//*[@class='dataTable']/tbody/tr[5]/td"));
		for (WebElement rowItem : fifthRow) {
			System.out.println(rowItem.getText());
		}
		
		System.out.println("*************Get Data of a 1st Column***************");
		
		//Get Data of a Column.
		List<WebElement> firstColumn = driver.findElements(By.xpath("//*[@class='dataTable']/tbody/tr/td[1]"));
		for (WebElement webElement : firstColumn) {
			System.out.println(webElement.getText());
		}
		
		System.out.println("*************Get the Complete Data***************");
		
		
		//Get the Complete Data.
		List<WebElement> allRows = driver.findElements(By.xpath("//*[@class='dataTable']/tbody/tr"));
		for (WebElement row : allRows) {
			System.out.println(row.getText());
		}
		
		System.out.println("*************Get Data From Specific Cell***************");
		
		//Get Data From Specific Cell.
		String cellValue = driver.findElement(By.xpath("//*[@class='dataTable']/tbody/tr[8]/td[3]")).getText();
		System.out.println(cellValue);

	}
	
	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.get("https://money.rediff.com/index.html");

	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
