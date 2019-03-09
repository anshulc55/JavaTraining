package com.selenium.masterpart3;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class HandleWebTable {
	
	WebDriver driver = null;
	
	@BeforeMethod
	public void openBrowser(){
		
		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		 driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Apply Implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}
	
	@Test
	public void manageWebTable(){
		
		driver.get("https://www.rediff.com/");
		driver.findElement(By.xpath("//*[@id='homewrapper']/div[5]/a[3]/div/u")).click();
		
		driver.findElement(By.xpath("//*[@id='moremoney']/ul/li[3]/a")).click();
		driver.findElement(By.xpath("//*[@id='showMoreLess']"));
		
		//Get Count Rows
		List<WebElement> totalRows = driver.findElements(By.xpath("//*[@class='dataTable']/tbody/tr"));
		System.out.println("Total number of Rows : " + totalRows.size());
		
		//Get Count Columns
		List<WebElement> totalCol = driver.findElements(By.xpath("//*[@class='dataTable']/tbody/tr[1]/td"));
		System.out.println("Total Number of Columns : " + totalCol.size());
		
		//Get the Data of Specific Row
		List<WebElement> columns = driver.findElements(By.xpath("//*[@class='dataTable']/tbody/tr[3]/td"));
		System.out.println("***********Data of 3rd Row************");
		for (WebElement column : columns) {
			System.out.println(column.getText());
		}
		
		//Get Data of a Column
		List<WebElement> column = driver.findElements(By.xpath("//*[@class='dataTable']/tbody/tr/td[1]"));
		System.out.println("****************Data of First Column***************");
		for (WebElement col : column) {
			System.out.println(col.getText());
		}
		
		//Print Data of Complete Table
		System.out.println("**********Complete Table Data*************");
		for (WebElement row : totalRows) {
			System.out.println(row.getText());
		}
			
		}
		
	}

