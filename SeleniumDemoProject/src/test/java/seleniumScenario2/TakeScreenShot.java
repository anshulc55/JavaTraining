package seleniumScenario2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TakeScreenShot {
	WebDriver driver = null;

	// Partial Screen Shot

	@Test
	public void captureScreenShot() throws IOException {
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

		driver.findElement(By.id("email")).sendKeys("anshulc55@icloud.com");
		takeScreenShot();

		driver.findElement(By.linkText("Privacy Centre")).click();
		Assert.assertEquals(driver.getTitle(), "Meta");

		WebElement menuBar = driver.findElement(By.xpath(
				"/html/body/div[1]/div/div[1]/div/div[2]/div/div/div[1]/div[1]/div/div[2]/div[1]/div[1]/div/div[1]/aside/div"));
		takeScreenShotOfSpecificArea(menuBar);

		WebElement widgets = driver.findElement(By.xpath(
				"/html/body/div[1]/div/div[1]/div/div[2]/div/div/div[1]/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[2]/div/div/div/div[2]/div/div"));
		takeScreenShotOfSpecificArea(widgets);
	}

	
	// Capture ScreenShot Funcation
	public void takeScreenShot() throws IOException {

		String filepath = System.getProperty("user.dir") + "/screenshots/" + generateFileName();
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(screenshotFile, new File(filepath));
	}

	
	// To Capture Specific Area of WebPage
	public void takeScreenShotOfSpecificArea(WebElement element) {
		// Get entire page screenshot
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg;

		try {
			fullImg = ImageIO.read(screenshot);

			// Get the location of element on the page
			Point point = element.getLocation();

			// Get width and height of the element
			int eleWidth = element.getSize().getWidth();
			int eleHeight = element.getSize().getHeight();

			// Crop the entire page screenshot to get only element screenshot
			BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
			ImageIO.write(eleScreenshot, "png", screenshot);

			// Copy the element screenshot to disk
			String filepath = System.getProperty("user.dir") + "/screenshots/" + generateFileName();
			FileUtils.copyFile(screenshot, new File(filepath));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Generate Screenshot Name
	public String generateFileName() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String date = dateformat.format(new Date());

		String filename = date + ".png";

		return filename;
	}

	@BeforeMethod
	public void init() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.get("https://en-gb.facebook.com");

	}

	@AfterMethod
	public void finish() {
		driver.quit();
	}

}
