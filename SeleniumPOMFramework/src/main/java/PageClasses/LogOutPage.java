package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class LogOutPage extends PageBaseClass {

	public TopMenuClass topmenu;

	public LogOutPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topmenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topmenu);
	}

	@FindBy(xpath = "//*[@id='wrapper']/div[4]/a")
	public WebElement loginAgainLink;

	public PortFolioLoginPage clickLoginAgain() {
		logger.log(Status.INFO, "Clicking the Login-Again Link");
		loginAgainLink.click();
		logger.log(Status.PASS, "Clicked the Login-Again Link Link");
		PortFolioLoginPage portfolioLogin = new PortFolioLoginPage(driver, logger);
		PageFactory.initElements(driver, portfolioLogin);
		return portfolioLogin;
	}

}
