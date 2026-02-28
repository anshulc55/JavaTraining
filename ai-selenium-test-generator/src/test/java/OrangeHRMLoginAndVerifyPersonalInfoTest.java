import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrangeHRMLoginAndVerifyPersonalInfoTest {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private final By usernameField = By.cssSelector("input[name='username']");
    private final By passwordField = By.cssSelector("input[name='password']");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By dashboardHeader = By.xpath("//header//h6[normalize-space()='Dashboard']");
    private final By myInfoMenuItem = By.xpath("//span[normalize-space()='My Info']");
    private final By personalDetailsHeader = By.xpath("//h6[normalize-space()='Personal Details']");
    private final By firstNameField = By.cssSelector("input[name='firstName']");

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Optional: run headless by setting -Dheadless=true
        if ("true".equalsIgnoreCase(System.getProperty("headless"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options); // Selenium Manager will resolve the driver
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "Login and Verify Personal Information on OrangeHRM Demo")
    public void testLoginAndVerifyPersonalInformation() {
        // Step 1: Navigate to URL and validate login fields are present
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
        WebElement usernameInput = waitForVisible(usernameField);
        WebElement passwordInput = waitForVisible(passwordField);
        Assert.assertTrue(usernameInput.isDisplayed() && usernameInput.isEnabled(), "Username field should be present and enabled.");
        Assert.assertTrue(passwordInput.isDisplayed() && passwordInput.isEnabled(), "Password field should be present and enabled.");

        // Step 2: Verify page title equals 'OrangeHRM'
        wait.until(ExpectedConditions.titleIs("OrangeHRM"));
        Assert.assertEquals(driver.getTitle(), "OrangeHRM", "Page title should be 'OrangeHRM'.");

        // Step 3: Enter username and validate
        usernameInput.clear();
        usernameInput.sendKeys("Admin");
        Assert.assertEquals(usernameInput.getAttribute("value"), "Admin", "Username field should contain 'Admin'.");

        // Step 4: Enter password and validate it's populated and masked
        passwordInput.clear();
        passwordInput.sendKeys("admin123");
        String passwordType = passwordInput.getAttribute("type");
        String passwordValue = passwordInput.getAttribute("value");
        Assert.assertEquals(passwordType, "password", "Password field type should be 'password' (masked).");
        Assert.assertTrue(passwordValue != null && !passwordValue.isEmpty(), "Password field should be populated.");

        // Step 5: Click Login and ensure navigation away from login page
        waitForClickable(loginButton).click();

        // Step 6: Verify Dashboard is displayed
        WebElement dashboard = waitForVisible(dashboardHeader);
        Assert.assertEquals(dashboard.getText().trim(), "Dashboard", "Dashboard header should be visible with text 'Dashboard'.");
        // Optional URL check if present
        // Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"), "URL should contain '/dashboard' after login.");

        // Step 7: Click 'My Info' from sidebar
        waitForClickable(myInfoMenuItem).click();

        // Step 8: Verify 'Personal Details' section is displayed
        WebElement personalDetails = waitForVisible(personalDetailsHeader);
        Assert.assertEquals(personalDetails.getText().trim(), "Personal Details", "'Personal Details' section header should be visible.");

        // Step 9: Read First Name field value
        WebElement firstNameInput = waitForVisible(firstNameField);
        String firstNameValue = firstNameInput.getAttribute("value");

        // Step 10: Print/Log the First Name value
        System.out.println("Captured First Name value: " + firstNameValue);

        // Step 11: Assert First Name is not empty
        Assert.assertTrue(firstNameValue != null && firstNameValue.trim().length() > 0, "First Name value should not be empty.");
    }

    // Helper methods
    private WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}