

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class OrangeHrmLoginAndVerifyPersonalInfoWithExtentTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentReports extent;
    private ExtentSparkReporter spark;
    private ExtentTest test;

    @BeforeClass
    public void setUp() {
        try {
            Files.createDirectories(Paths.get("reports", "screenshots"));
        } catch (IOException ignored) {
        }
        spark = new ExtentSparkReporter("reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
    }

    @Test(description = "Validate that a registered user can log in to the OrangeHRM demo site, navigate to My Info, and verify the Personal Details First Name is non-empty.")
    public void testLoginAndVerifyPersonalInformationOnOrangeHRMDemo() throws IOException {
        test = extent.createTest("Login and Verify Personal Information on OrangeHRM Demo");
        String firstNameValue = null;

        try {
            // Step 1: Navigate to URL
            String baseUrl = "https://opensource-demo.orangehrmlive.com/";
            test.info("Step 1: Navigate to URL: " + baseUrl);
            driver.get(baseUrl);
            By usernameLocator = By.cssSelector("input[name='username']");
            By passwordLocator = By.cssSelector("input[name='password']");
            waitUntilVisible(usernameLocator);
            waitUntilVisible(passwordLocator);
            test.pass("Login page loads successfully (username and password fields are present).");

            // Step 2: Verify page title
            test.info("Step 2: Verify page title equals 'OrangeHRM'.");
            wait.until(ExpectedConditions.titleContains("OrangeHRM"));
            Assert.assertEquals(driver.getTitle(), "OrangeHRM", "Expected title to be 'OrangeHRM'.");
            test.pass("Title equals 'OrangeHRM'.");

            // Step 3: Enter Username
            test.info("Step 3: Enter text into Username field: 'Admin'.");
            WebElement usernameField = driver.findElement(usernameLocator);
            usernameField.clear();
            usernameField.sendKeys("Admin");
            Assert.assertEquals(usernameField.getAttribute("value"), "Admin", "Username field does not contain 'Admin'.");
            test.pass("Username field contains 'Admin'.");

            // Step 4: Enter Password
            test.info("Step 4: Enter text into Password field: 'admin123'.");
            WebElement passwordField = driver.findElement(passwordLocator);
            passwordField.clear();
            passwordField.sendKeys("admin123");
            Assert.assertTrue(passwordField.getAttribute("value").length() > 0, "Password field is empty.");
            Assert.assertEquals(passwordField.getAttribute("type"), "password", "Password field is not masked.");
            test.pass("Password field is populated and masked.");

            // Step 5: Click Login button
            test.info("Step 5: Click on Login button.");
            By loginButton = By.cssSelector("button[type='submit']");
            waitUntilClickable(loginButton).click();
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/dashboard"),
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("header h6"))
            ));
            Assert.assertFalse(driver.getCurrentUrl().contains("/auth/login"), "User is still on login page after attempting to login.");
            test.pass("User is authenticated and navigated away from login page.");

            // Step 6: Verify Dashboard header
            test.info("Step 6: Verify 'Dashboard' header is visible.");
            WebElement dashboardHeader = waitUntilVisible(By.cssSelector("header h6"));
            Assert.assertEquals(dashboardHeader.getText().trim(), "Dashboard", "Dashboard header text mismatch.");
            Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"), "URL does not contain '/dashboard'.");
            test.pass("Dashboard page is displayed (header 'Dashboard' visible; URL contains '/dashboard').");

            // Step 7: Click My Info
            test.info("Step 7: Click sidebar menu item 'My Info'.");
            By myInfoMenu = By.xpath("//span[normalize-space()='My Info']");
            waitUntilClickable(myInfoMenu).click();
            test.pass("Navigation to 'My Info' page initiated.");

            // Step 8: Verify Personal Details header
            test.info("Step 8: Verify 'Personal Details' section header is visible.");
            WebElement personalDetailsHeader = waitUntilVisible(By.xpath("//h6[normalize-space()='Personal Details']"));
            Assert.assertEquals(personalDetailsHeader.getText().trim(), "Personal Details", "Personal Details header not visible.");
            test.pass("'Personal Details' section is displayed.");

            // Step 9: Read First Name
            test.info("Step 9: Read value from 'First Name' field.");
            WebElement firstNameField = waitUntilVisible(By.cssSelector("input[name='firstName']"));
            firstNameValue = firstNameField.getAttribute("value");
            test.pass("Captured 'firstNameValue' successfully.");

            // Step 10: Log First Name value
            test.info("Step 10: Print/Log 'firstNameValue'.");
            System.out.println("Captured First Name: " + firstNameValue);
            test.pass("The value of 'firstNameValue' is: " + firstNameValue);

            // Step 11: Assert Not Empty
            test.info("Step 11: Assert that 'firstNameValue' is not empty.");
            Assert.assertTrue(firstNameValue != null && firstNameValue.trim().length() > 0, "'firstNameValue' is empty.");
            test.pass("Assertion passed: 'firstNameValue' is not empty.");

        } catch (Exception e) {
            String screenshotPath = captureScreenshot("failure");
            test.fail("Test failed with exception: " + e.getMessage(),
			        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            Assert.fail("Test failed due to exception: " + e.getMessage(), e);
        }
    }

    private WebElement waitUntilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private String captureScreenshot(String namePrefix) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        String fileName = namePrefix + "_" + timestamp + ".png";
        Path destination = Paths.get("reports", "screenshots", fileName);
        try {
            Files.createDirectories(destination.getParent());
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            try {
                byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Files.write(destination, bytes);
            } catch (IOException ignored) {
            }
        }
        return destination.toString();
    }
}