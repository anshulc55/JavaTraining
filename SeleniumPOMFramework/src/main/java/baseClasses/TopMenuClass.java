package baseClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import PageClasses.LogOutPage;

public class TopMenuClass extends PageBaseClass {

	public TopMenuClass(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	@FindBy(xpath = "//*[@id='wrapper']/div[3]/ul/li[2]/a")
	public WebElement myPortfolioLink;

	@FindBy(xpath = "//*[@id='signin_info']/a")
	public WebElement singoutLink;

	public LogOutPage singOutApplication() {
		logger.log(Status.INFO, "Clicking the Sing-Out Link");
		singoutLink.click();
		logger.log(Status.PASS, "Clicked the Sing-Out Link");
		LogOutPage logoutPage = new LogOutPage(driver, logger);
		PageFactory.initElements(driver, logoutPage);
		return logoutPage;
	}

}
