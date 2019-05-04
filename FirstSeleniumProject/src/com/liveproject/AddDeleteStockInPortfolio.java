package com.liveproject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddDeleteStockInPortfolio {

	WebDriver driver = null;

	@Test
	public void addStockTest() {

		System.setProperty("webdriver.chrome.driver",
				"/Users/anshul/Trainings/JavaProjectTraining/FirstSeleniumProject/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Apply Implicit wait
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);

		driver.get("https://www.rediff.com/");
		// Click the Money Link
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/a[2]")).click();

		driver.findElement(By.xpath("//*[@id='signin_info']/a[1]")).click();

		driver.findElement(By.xpath("//*[@id='useremail']")).sendKeys("anshulc55@rediffmail.com");
		driver.findElement(By.xpath("//*[@id='emailsubmit']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='userpass']"))));

		driver.findElement(By.xpath("//*[@id='userpass']")).sendKeys("Test@1234");
		driver.findElement(By.xpath("//*[@id='userpass']")).sendKeys(Keys.ENTER);

		// Selecting the Portfolio
		WebElement dropdown = driver.findElement(By.id("portfolioid"));
		Select select = new Select(dropdown);
		select.selectByVisibleText("Test1");
		
		waitForPageLoad();

		// Add Stock
		driver.findElement(By.id("addStock")).click();
		driver.findElement(By.id("addstockname")).sendKeys("Nestle India");

		driver.findElement(By.xpath("//div[@id='ajax_listOfOptions']/div[1]")).click();

		// 1st Method to enter Date
		// driver.findElement(By.id("stockAddDate")).sendKeys("12-03-2018");

		// 2nd Method to Enter Date
		driver.findElement(By.id("stockPurchaseDate")).click();
		selectDateIncalendar("13/06/2017");

		driver.findElement(By.id("addstockqty")).sendKeys("150");
		driver.findElement(By.id("addstockprice")).sendKeys("10000");

		driver.findElement(By.id("addStockButton")).click();
		
		waitForPageLoad();

		// Verify Stock
		// String stockName =
		// driver.findElement(By.xpath("//table[@id='stock']/tbody/tr[2]/td[2]/span/a")).getText();
		// Assert.assertEquals(stockName, "Nestle India");

		int stockRowNum = getStockRowNumber("Nestle India");

		System.out.println("Row number is : " + stockRowNum);
		if (stockRowNum == -1) {
			Assert.fail("Stock not Found");
		}

	}

	@Test(dependsOnMethods = "addStockTest")
	public void deleteStock() {

		int stockRowNum = getStockRowNumber("Nestle India");
		driver.findElement(By.xpath("//table[@id='stock']/tbody/tr[" + stockRowNum + "]/td[1]")).click();
		driver.findElements(By.xpath("//input[@name='Delete']")).get(stockRowNum - 1).click();

		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();

		waitForPageLoad();

		int stockRowNumAfterDelete = getStockRowNumber("Nestle India");
		Assert.assertEquals(stockRowNumAfterDelete, -1, "Row is Deleted");
	}

	public int getStockRowNumber(String StockName) {

		List<WebElement> totalRows = driver.findElements(By.xpath("//table[@id='stock']/tbody/tr"));

		int rowNum = 0;
		for (WebElement row : totalRows) {
			List<WebElement> rowCells = row.findElements(By.tagName("td"));
			rowNum++;

			for (WebElement cell : rowCells) {
				String cellData = cell.getText();

				if (!cellData.isEmpty() && cellData.contains(StockName)) {
					return rowNum;
				}

			}

		}
		return -1;
	}

	public void waitForPageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int i = 0;
		while (i != 180) {
			String pageState = (String) js.executeScript("return document.readyState;");
			if (pageState.equals("complete")) {
				break;
			} else {
				waitLoad(1);
			}
		}

		waitLoad(2);

		i = 0;
		while (i != 180) {
			Boolean jsState = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
			if (jsState) {
				break;
			} else {
				waitLoad(1);
			}
		}
	}

	public void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectDateIncalendar(String date) {

		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date expectedDate = dateFormat.parse(date);

			String day = new SimpleDateFormat("dd").format(expectedDate);
			String month = new SimpleDateFormat("MMMM").format(expectedDate);
			String year = new SimpleDateFormat("yyyy").format(expectedDate);

			String expectedMonthYear = month + " " + year;

			while (true) {
				String displayDate = driver.findElement(By.className("dpTitleText")).getText();

				if (expectedMonthYear.equals(displayDate)) {

					driver.findElement(By.xpath("//td[text()= '" + day + "']")).click();

					break;
				} else if (expectedDate.compareTo(currentDate) > 0) {
					driver.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[4]/button")).click();
				} else {
					driver.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[2]/button")).click();
				}

			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
