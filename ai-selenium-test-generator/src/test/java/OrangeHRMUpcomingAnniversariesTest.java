import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrangeHRMUpcomingAnniversariesTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/";
    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "admin123";

    @BeforeClass(alwaysRun = true)
    public void setUpChromeDriver() {
        //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless=new"); // Uncomment if you want to run headless
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            try {
                // Try to log out if still logged in
                openUserDropdownIfPresent();
                clickIfPresent(By.xpath("//a[normalize-space()='Logout']"));
            } catch (Exception ignored) {
            } finally {
                driver.quit();
            }
        }
    }

    @Test(description = "Verify Upcoming Anniversaries list on Buzz page and print entries")
    public void verifyAndPrintUpcomingAnniversaries() {
        // 1) Open home page
        driver.get(BASE_URL);

        // 2) Log in with valid credentials
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(USERNAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Wait for post-login landing (Dashboard visible or sidebar present)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.oxd-sidepanel")));

        // 3) Navigate to Buzz section via sidebar
        By buzzMenu = By.xpath("//span[normalize-space()='Buzz']/ancestor::a");
        wait.until(ExpectedConditions.elementToBeClickable(buzzMenu)).click();

        // Wait for Buzz page header to appear
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='Buzz']")),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(normalize-space(),'Buzz')]"))
        ));

        // 4) Verify "Upcoming Anniversaries" panel is visible (scroll into view if needed)
        WebElement anniversariesHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[normalize-space()='Upcoming Anniversaries' or contains(.,'Upcoming Anniversaries')]")
        ));
        scrollIntoView(anniversariesHeader);

        // The container/card that holds the panel body
        WebElement anniversariesPanelContainer = anniversariesHeader.findElement(By.xpath("./ancestor::div[contains(@class,'oxd-card') or contains(@class,'orangehrm')][1]"));
        Assert.assertTrue(anniversariesPanelContainer.isDisplayed(), "'Upcoming Anniversaries' panel should be visible");

        // 5) If the panel is collapsed or initially empty, try expanding by clicking header
        List<WebElement> entryCandidates = findAnniversaryEntryContainers(anniversariesPanelContainer);
        if (entryCandidates.isEmpty()) {
            safeClick(anniversariesHeader); // attempt to expand
            // small wait for expand animation/content load
            sleep(500);
            entryCandidates = findAnniversaryEntryContainers(anniversariesPanelContainer);
        }

        // 6) Extract names (and optionally dates) under "Upcoming Anniversaries"
        List<String> formattedEntries = extractNameAndDateFromAnniversaryItems(entryCandidates);

        // 7) Assert at least one entry is present and print them
        Assert.assertTrue(formattedEntries.size() > 0, "Expected at least one upcoming anniversary entry");
        String output = "Upcoming Anniversaries: [" + String.join(", ", formattedEntries) + "]";
        System.out.println(output);

        // 8) Clean up: log out
        openUserDropdownIfPresent();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Logout']"))).click();

        // Verify logout brings back login form
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
    }

    // Helper: Attempt to find each anniversary entry container in the panel
    private List<WebElement> findAnniversaryEntryContainers(WebElement panelContainer) {
        // Try common structures used in OrangeHRM sidebars
        List<WebElement> items = new ArrayList<>();

        // Most specific guess: employee details cards in the buzz sidebar
        items.addAll(panelContainer.findElements(By.xpath(".//div[contains(@class,'employee-details') or contains(@class,'employee')][.//p]")));

        // Fallbacks: list items or generic rows
        if (items.isEmpty()) {
            items.addAll(panelContainer.findElements(By.xpath(".//li[.//p]")));
        }
        if (items.isEmpty()) {
            items.addAll(panelContainer.findElements(By.xpath(".//div[contains(@class,'list') or contains(@class,'item') or contains(@class,'row')][.//p]")));
        }

        // Filter out containers that don't seem like person rows (e.g., have no text p children)
        return items.stream()
                .filter(e -> !e.findElements(By.xpath(".//p[normalize-space() != '' and not(contains(.,'Upcoming Anniversaries'))]")).isEmpty())
                .collect(Collectors.toList());
    }

    // Helper: Extract name and optional date from each entry container
    private List<String> extractNameAndDateFromAnniversaryItems(List<WebElement> entryContainers) {
        List<String> results = new ArrayList<>();
        for (WebElement container : entryContainers) {
            // Heuristic: The first p is often the name; second p may contain date or tenure info
            String name = getTextOrEmpty(container, By.xpath(".//p[normalize-space()!=''][1]")).trim();

            // Try to find a likely date or anniversary descriptor in the same container
            String dateOrInfo = "";
            List<By> dateLocators = List.of(
                    By.xpath(".//p[contains(translate(.,'JFMASOND','jfmasond'),'jan') or contains(translate(.,'JFMASOND','jfmasond'),'feb') or contains(translate(.,'JFMASOND','jfmasond'),'mar') or contains(translate(.,'JFMASOND','jfmasond'),'apr') or contains(translate(.,'JFMASOND','jfmasond'),'may') or contains(translate(.,'JFMASOND','jfmasond'),'jun') or contains(translate(.,'JFMASOND','jfmasond'),'jul') or contains(translate(.,'JFMASOND','jfmasond'),'aug') or contains(translate(.,'JFMASOND','jfmasond'),'sep') or contains(translate(.,'JFMASOND','jfmasond'),'oct') or contains(translate(.,'JFMASOND','jfmasond'),'nov') or contains(translate(.,'JFMASOND','jfmasond'),'dec')]"),
                    By.xpath(".//p[contains(.,'-') or contains(.,'/') or contains(.,',')]"),
                    By.xpath(".//p[matches(., '.*[0-9].*')]") // will be ignored by drivers without XPath 2.0, but kept as last attempt
            );

            for (By locator : dateLocators) {
                List<WebElement> candidates = container.findElements(locator);
                if (!candidates.isEmpty()) {
                    // Choose the last texty element (often the most specific)
                    String candidate = candidates.get(candidates.size() - 1).getText().trim();
                    // Avoid accidentally picking the same as name
                    if (!candidate.equalsIgnoreCase(name) && candidate.length() >= 3) {
                        dateOrInfo = candidate;
                        break;
                    }
                }
            }

            if (name.isEmpty()) {
                // Skip empty/invalid rows
                continue;
            }

            if (!dateOrInfo.isEmpty()) {
                results.add(name + " - " + dateOrInfo);
            } else {
                results.add(name);
            }
        }
        return results;
    }

    private void openUserDropdownIfPresent() {
        // The user dropdown toggle can be a p.oxd-userdropdown-name or span.oxd-userdropdown-tab depending on build
        By[] toggles = new By[] {
                By.cssSelector("p.oxd-userdropdown-name"),
                By.cssSelector("span.oxd-userdropdown-tab"),
                By.xpath("//span[contains(@class,'oxd-userdropdown-tab') or contains(@class,'userdropdown')]")
        };
        for (By toggle : toggles) {
            List<WebElement> els = driver.findElements(toggle);
            if (!els.isEmpty()) {
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(els.get(0))).click();
                    return;
                } catch (Exception ignored) { }
            }
        }
    }

    private void scrollIntoView(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);
        } catch (JavascriptException ignored) {
        }
    }

    private void safeClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            try {
                new Actions(driver).moveToElement(element).click().perform();
            } catch (Exception ignored) {
            }
        }
    }

    private void clickIfPresent(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        if (!elements.isEmpty()) {
            safeClick(elements.get(0));
        }
    }

    private String getTextOrEmpty(WebElement scope, By locator) {
        try {
            WebElement el = scope.findElement(locator);
            return el.getText() != null ? el.getText() : "";
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}