package keywords;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import reports.ExtentManager;

public class GenericKeywords {
	public WebDriver driver;
	public Properties prop;
	public ExtentTest test;
	public SoftAssert softAssert;

	// Acceptable Failures, Critical Failures, Unexpected Failures

	public void openBrowser(String browserKey) {
		String browserName = prop.getProperty(browserKey);
		logInfo("Opening Browser -- " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized", "--disable-infobars");
			options.addArguments("--disable-extensions");
			//options.addArguments("--incognito");
			options.addArguments("--disable-notifications");
			//options.addArguments("ignore-certificate-errors");

			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "drivers/geckodriver");
			FirefoxOptions options = new FirefoxOptions();
			ProfilesIni profiles = new ProfilesIni();
			FirefoxProfile ffprofile = profiles.getProfile("TestUser");
			ffprofile.setPreference("dom.webnotifications.enabled", false);
			ffprofile.setAcceptUntrustedCertificates(true);
			ffprofile.setAssumeUntrustedCertificateIssuer(false);
			options.setProfile(ffprofile);
			driver = new FirefoxDriver(options);
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "drivers/msedgedriver");
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "drivers/chromedriver");
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void setReport(ExtentTest test) {
		this.test = test;
	}

	public void reportFailure(String msg, boolean isCriticalFailure) {
		logError(msg);
		takeScreenShot();
		softAssert.fail(msg);

		if (isCriticalFailure) {
			Reporter.getCurrentTestResult().getTestContext().setAttribute("isCriticalFailure", "true");
			reportAll();
		}
	}

	public void reportFailure(String msg) {
		// logError(msg);
		// softAssert.fail(msg);
		reportFailure(msg, false);
	}

	public void reportAll() {
		softAssert.assertAll();
	}

	public void takeScreenShot() {
		// File Name of Screenshot
		Date currentDate = new Date();

		// Format the date and time
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String formattedDate = dateFormat.format(currentDate);

		String screenshotFile = formattedDate + ".png";

		// take screenshot
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenShotPath + '/' + screenshotFile));

			// put screenshot file in Extent reports
			// test.log(Status.INFO, "Screenshot -- "
			// + test.addScreenCaptureFromPath(ExtentManager.screenShotPath + '/' +
			// screenshotFile));

			test.log(Status.FAIL, MarkupHelper.createLabel("Screenshot", ExtentColor.GREEN));
			test.log(Status.FAIL,
					"<img src='" + ExtentManager.screenShotPath + '/' + screenshotFile + "' style='width: 100%;' />");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void openURL(String URLKey) {
		logInfo("Opening Web URL : " + prop.getProperty(URLKey));
		driver.get(prop.getProperty(URLKey));
	}

	public void click(String locatorKey) {
		logInfo("Perform Click on Locatore : " + prop.getProperty(locatorKey));
		getElement(locatorKey).click();
		// driver.findElement(By.linkText(prop.getProperty(locatorKey))).click();
	}
	
	public void clickEnterKey(String locatorKey) {
		logInfo("Pressing Enter Key");
		getElement(locatorKey).sendKeys(Keys.ENTER);
	}

	public void clickButton(String locatorKey) {
		logInfo("Perform Click on Locatore : " + prop.getProperty(locatorKey));
		getElement(locatorKey).click();
	}

	public void type(String locatorKey, String value) {
		logInfo("Typing Text : " + value + " - In Locator : " + prop.getProperty(locatorKey));
		getElement(locatorKey).sendKeys(value);
	}
	
	public void selectByVisibleText(String locatorKey, String value) {
		logInfo("Selecting Portfolio :: " + value);
		Select dropDown = new Select(getElement(locatorKey));
		dropDown.selectByVisibleText(value);
		
	}
	
	public void acceptAlert() {
		logInfo("Accepting the Alert");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.alertIsPresent());
		
		driver.switchTo().alert().accept();
		logInfo("Accepted the Alert Successfully");
	}

	public void enterCaptcha(String locatorKey) throws InterruptedException {
		logInfo("Given Captcha Locator : " + prop.getProperty(locatorKey));
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter Captcha to fill in the text box: ");
			String inputText = scanner.nextLine();

			getElement(locatorKey).sendKeys(inputText);
		}
		Thread.sleep(3000);
	}

	public WebElement getElement(String locatorKey) {

//		// Element is present
//		if (!isElementPresent(locatorKey)) {
//			// Report Error
//			System.out.println("Element is not Present : " + locatorKey);
//		}
//
//		// Element is visible
//		if (!isElementVisible(locatorKey)) {
//			// Report Error
//			System.out.println("Element is not Visible : " + locatorKey);
//		}

		// Create WebElement and Return WebElement
		WebElement element = driver.findElement(getLocator(locatorKey));
		return element;
	}

	public List<WebElement> getElements(String locatorKey) {

		// Create WebElement and Return WebElement
		List<WebElement> elements = driver.findElements(getLocator(locatorKey));
		return elements;
	}

	public boolean isElementPresent(String locatorKey) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locatorKey)));
		} catch (Exception e) {
			reportFailure("Unable to locate Element with Locator : " + getLocator(locatorKey));
			reportFailure(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isElementVisible(String locatorKey) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorKey)));
		} catch (Exception e) {
			reportFailure(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public By getLocator(String locatorkey) {
		By by = null;

		if (locatorkey.endsWith("_id"))
			by = By.id(prop.getProperty(locatorkey));
		else if (locatorkey.endsWith("_xpath"))
			by = By.xpath(prop.getProperty(locatorkey));
		else if (locatorkey.endsWith("_css"))
			by = By.cssSelector(prop.getProperty(locatorkey));
		else if (locatorkey.endsWith("_linkText"))
			by = By.linkText(prop.getProperty(locatorkey));
		else if (locatorkey.endsWith("_partialLinkText"))
			by = By.partialLinkText(prop.getProperty(locatorkey));
		else if (locatorkey.endsWith("_name"))
			by = By.name(prop.getProperty(locatorkey));
		else if (locatorkey.endsWith("_className"))
			by = By.className(prop.getProperty(locatorkey));
		else if (locatorkey.endsWith("_name"))
			by = By.name(prop.getProperty(locatorkey));
		else if (locatorkey.endsWith("_tagName"))
			by = By.tagName(prop.getProperty(locatorkey));
		return by;
	}

	public void logInfo(String msg) {
		test.log(Status.INFO, msg);
	}

	public void logError(String msg) {
		test.log(Status.FAIL, msg);
	}

	public void logWarning(String msg) {
		test.log(Status.WARNING, msg);
	}

	public void logSkip(String msg) {
		test.log(Status.SKIP, msg);
	}

	public void clear(String locatorKey) {
		logInfo("Clear the Default Text from : " + locatorKey);
		getElement(locatorKey).clear();
	}

	public void select() {

	}

	public String getText(String locatorKey) {
		logInfo("Getting the Text from : " + locatorKey);
		return getElement(locatorKey).getText();
	}

	public void navigate() {

	}

	public void dismissAlert() {

	}

	public void quitDriver() {
		driver.quit();
	}

	public void waitforWebPageToLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int i = 0;

		while (i != 10) {
			String state = (String) js.executeScript("return document.readyState;");
			System.out.println(state);

			if (state.equals("complete"))
				break;
			else
				wait(2);

			i++;
		}

		// check for jQuery status
		i = 0;
		while (i != 10) {

			Long d = (Long) js.executeScript("return jQuery.active;");
			System.out.println(d);
			if (d.longValue() == 0)
				break;
			else
				wait(2);
			i++;

		}
	}

	public void wait(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
