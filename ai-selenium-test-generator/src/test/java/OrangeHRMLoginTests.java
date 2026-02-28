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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;

import java.time.Duration;
import java.util.List;

public class OrangeHRMLoginTests {

    private WebDriver driver;
    private WebDriverWait wait;
    ExtentReports extent;

    // Test data and URLs
    private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/";
    private static final String VALID_USERNAME = "Admin";
    private static final String VALID_PASSWORD = "admin123";
    private static final String INVALID_PASSWORD = "wrongpass";
    private static final String EXPECTED_TITLE = "OrangeHRM";

    // Locators
    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By loginFormContainer = By.cssSelector("div.orangehrm-login-form");

    private final By dashboardHeader = By.xpath("//h6[normalize-space()='Dashboard']");
    private final By userMenuName = By.cssSelector("p.oxd-userdropdown-name");
    private final By userMenuAvatar = By.cssSelector("img.oxd-userdropdown-img");
    private final By logoutLink = By.xpath("//a[normalize-space()='Logout']");

    private final By invalidCredentialsAlert = By.xpath("//p[contains(@class,'oxd-alert-content-text') and contains(normalize-space(),'Invalid credentials')]");

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // options.addArguments("--headless=new"); // Uncomment for headless execution if needed
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void navigateToLoginAndEnsureLoggedOut() {
        driver.get(BASE_URL);
        // If already logged in, log out to start from the login page for each test
        if (!isElementVisibleQuick(loginFormContainer)) {
            if (isElementVisibleQuick(userMenuName) || isElementVisibleQuick(userMenuAvatar)) {
                openUserMenu();
                safeClick(logoutLink);
            }
        }
        waitUntilVisible(loginFormContainer);
        wait.until(ExpectedConditions.titleIs(EXPECTED_TITLE));
    }

    @Test(description = "Login with valid credentials - verify Dashboard visibility and URL contains /dashboard")
    public void loginWithValidCredentials_VerifyDashboardVisibility() {
        // Step 1-2: On login page, verify title
        Assert.assertEquals(driver.getTitle(), EXPECTED_TITLE, "Login page title should be 'OrangeHRM'.");

        // Step 3-4: Enter username and password
        type(usernameField, VALID_USERNAME, true);
        Assert.assertEquals(getAttribute(usernameField, "value"), VALID_USERNAME, "Username field should contain 'Admin'.");

        type(passwordField, VALID_PASSWORD, true);
        // Can't assert masked content directly; verify field is of type 'password'
        Assert.assertEquals(getAttribute(passwordField, "type"), "password", "Password field should be masked.");

        // Step 5: Click Login
        safeClick(loginButton);

        // Step 6: Verify Dashboard header visible
        waitUntilVisible(dashboardHeader);
        Assert.assertTrue(isElementVisible(dashboardHeader), "Dashboard header with text 'Dashboard' should be visible.");

        // Step 7: Verify URL contains '/dashboard'
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"), "URL should contain '/dashboard' after successful login.");
    }

    @Test(description = "Login with valid credentials - verify Page Title after login and topbar user menu visible")
    public void loginWithValidCredentials_VerifyTitleAndTopbar() {
        // Step 1-2: On login page, verify title
        Assert.assertEquals(driver.getTitle(), EXPECTED_TITLE, "Login page title should be 'OrangeHRM'.");

        // Step 3-4: Enter username and password
        type(usernameField, VALID_USERNAME, true);
        Assert.assertEquals(getAttribute(usernameField, "value"), VALID_USERNAME, "Username field should contain 'Admin'.");
        type(passwordField, VALID_PASSWORD, true);
        Assert.assertEquals(getAttribute(passwordField, "type"), "password", "Password field should be masked.");

        // Step 5: Click Login
        safeClick(loginButton);

        // Step 6: Verify page title remains 'OrangeHRM' after login
        wait.until(ExpectedConditions.titleIs(EXPECTED_TITLE));
        Assert.assertEquals(driver.getTitle(), EXPECTED_TITLE, "Page title should remain 'OrangeHRM' after login.");

        // Step 7: Verify user/topbar menu is visible
        // Accept either avatar or user name as valid topbar/authenticated indicator
        boolean topbarVisible = waitUntilAnyVisible(userMenuAvatar, userMenuName);
        Assert.assertTrue(topbarVisible, "User/topbar menu should be visible, indicating an authenticated session.");
    }

    @Test(description = "Login with invalid password - verify error handling and that user remains on login page")
    public void loginWithInvalidPassword_VerifyErrorHandling() {
        // Step 1-2: On login page, verify title
        Assert.assertEquals(driver.getTitle(), EXPECTED_TITLE, "Login page title should be 'OrangeHRM'.");

        // Step 3-4: Enter username and invalid password
        type(usernameField, VALID_USERNAME, true);
        Assert.assertEquals(getAttribute(usernameField, "value"), VALID_USERNAME, "Username field should contain 'Admin'.");
        type(passwordField, INVALID_PASSWORD, true);
        Assert.assertEquals(getAttribute(passwordField, "type"), "password", "Password field should be masked.");

        // Step 5: Click Login
        safeClick(loginButton);

        // Step 6: Verify error message 'Invalid credentials' is displayed
        waitUntilVisible(invalidCredentialsAlert);
        Assert.assertTrue(isElementVisible(invalidCredentialsAlert), "Error message 'Invalid credentials' should be displayed.");

        // Step 7: Verify user remains on login page (URL contains '/auth/login')
        wait.until(ExpectedConditions.urlContains("/auth/login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/auth/login"),
                "User should remain on the login page; URL should contain '/auth/login'.");

        // Step 8: Verify Dashboard is not visible
        Assert.assertFalse(isElementVisibleQuick(dashboardHeader), "Dashboard should not be visible after failed login.");
    }

    // Helper methods

    private void type(By locator, String text, boolean clear) {
        WebElement el = waitUntilVisible(locator);
        if (clear) el.clear();
        el.sendKeys(text);
    }

    private void safeClick(By locator) {
        WebElement el = waitUntilClickable(locator);
        el.click();
    }

    private String getAttribute(By locator, String attribute) {
        return waitUntilVisible(locator).getAttribute(attribute);
    }

    private WebElement waitUntilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private boolean isElementVisible(By locator) {
        try {
            WebElement el = waitUntilVisible(locator);
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isElementVisibleQuick(By locator) {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement el = shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean waitUntilAnyVisible(By... locators) {
        long end = System.currentTimeMillis() + Duration.ofSeconds(10).toMillis();
        while (System.currentTimeMillis() < end) {
            for (By locator : locators) {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    return true;
                }
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException ignored) {
            }
        }
        return false;
    }

    private void openUserMenu() {
        // Try avatar first, then user name, to open dropdown menu
        if (isElementVisibleQuick(userMenuAvatar)) {
            safeClick(userMenuAvatar);
        } else {
            safeClick(userMenuName);
        }
        waitUntilVisible(logoutLink);
    }
}