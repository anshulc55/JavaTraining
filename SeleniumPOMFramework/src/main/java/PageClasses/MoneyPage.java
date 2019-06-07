package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class MoneyPage extends PageBaseClass {

	public TopMenuClass topmenu;

	public MoneyPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topmenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topmenu);
	}

	@FindBy(xpath = "//*[@id='signin_info']/a[1]")
	public WebElement signinLink;

	public PortFolioLoginPage clickSingInLink() {
		logger.log(Status.INFO, "Clicking the Sing-In Link");
		signinLink.click();
		logger.log(Status.PASS, "Clicked the Sing-In Link");
		PortFolioLoginPage portfolioLogin = new PortFolioLoginPage(driver, logger);
		PageFactory.initElements(driver, portfolioLogin);
		return portfolioLogin;
	}

}
