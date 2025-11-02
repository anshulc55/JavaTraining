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

public class OrangeHRMLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/";
    private static final String VALID_USERNAME = "Admin";
    private static final String VALID_PASSWORD = "admin123";
    private static final String EXPECTED_LOGIN_TITLE = "OrangeHRM";

    // Locators
    private static final By USERNAME_INPUT = By.name("username");
    private static final By PASSWORD_INPUT = By.name("password");
    private static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    private static final By DASHBOARD_HEADER = By.cssSelector("h6.oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module");

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        // Selenium 4.6+ will manage the ChromeDriver binary automatically
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless=new"); // Uncomment for headless runs in CI
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "As a registered user, I can log in with valid credentials and access the dashboard")
    public void userCanLoginWithValidCredentials() {
        // Navigate to the application
        driver.get(BASE_URL);

        // Ensure login page is loaded
        waitForVisible(USERNAME_INPUT);

        // Verify the Page Title
        String actualLoginTitle = driver.getTitle();
        Assert.assertEquals(actualLoginTitle, EXPECTED_LOGIN_TITLE, "Login page title should match expected.");

        // Enter valid username and password and submit
        login(VALID_USERNAME, VALID_PASSWORD);

        // Verify successful login by checking that the Dashboard is visible
        WebElement dashboardHeading = waitForVisible(DASHBOARD_HEADER);
        Assert.assertTrue(dashboardHeading.isDisplayed(), "Dashboard heading should be visible after login.");
        Assert.assertEquals(dashboardHeading.getText().trim(), "Dashboard", "User should land on the Dashboard.");

        // Optional: Also verify URL contains 'dashboard' and title remains consistent
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("dashboard"),
                "Current URL should contain 'dashboard' after login.");
        Assert.assertEquals(driver.getTitle(), EXPECTED_LOGIN_TITLE, "Page title remains consistent after login.");
    }

    // Helper methods

    private void login(String username, String password) {
        WebElement usernameField = waitForVisible(USERNAME_INPUT);
        WebElement passwordField = waitForVisible(PASSWORD_INPUT);
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    private WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}