package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class PortFolioLoginPage extends PageBaseClass {

	public TopMenuClass topmenu;

	public PortFolioLoginPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topmenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topmenu);
	}

	@FindBy(id = "useremail")
	public WebElement useremail_TextBox;

	@FindBy(id = "emailsubmit")
	public WebElement emailSubmit_button;

	@FindBy(id = "userpass")
	public WebElement password_TextBox;

	@FindBy(id = "loginsubmit")
	public WebElement submitLogin_button;

	public MyPorfolioPage doLogin(String userName, String password) {
		
		useremail_TextBox.sendKeys(userName);
		logger.log(Status.PASS, "Entered the UserName : " + userName);
		emailSubmit_button.click();
		waitForPageLoad();
		logger.log(Status.PASS, "Clicked the UserName Submit Buttton");
		password_TextBox.sendKeys(password);
		logger.log(Status.PASS, "Entered the Password : " + password);
		submitLogin_button.click();
		waitForPageLoad();
		logger.log(Status.PASS, "Cliecked the Submit Login Button");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String currentPageTitle = driver.getTitle();
		if (currentPageTitle.equals("Indian stock markets: Login to Portfolio")){
			reportFail("Login is failed on Portfolio Login Page");
			Assert.fail("Login Failed");
		}
		MyPorfolioPage myportFolioPage = new MyPorfolioPage(driver, logger);
		PageFactory.initElements(driver, myportFolioPage);
		return myportFolioPage;
	}
	
	public void enterUser(String userName){
		useremail_TextBox.sendKeys(userName);
		logger.log(Status.PASS, "Entered the UserName : " + userName);
	}
	
	public void submitUserName(){
		emailSubmit_button.click();
		logger.log(Status.PASS, "Clicked the UserName Submit Buttton");
	}
	
	public void enterPassword(String password){
		password_TextBox.sendKeys(password);
		logger.log(Status.PASS, "Entered the Password : " + password);
	}
	
	public MyPorfolioPage submitLogin(){
		submitLogin_button.click();
		logger.log(Status.PASS, "Cliecked the Submit Login Button");
		MyPorfolioPage myportFolioPage = new MyPorfolioPage(driver, logger);
		PageFactory.initElements(driver, myportFolioPage);
		return myportFolioPage;
	}
	
	public void verifyPasswordField(){
		veriyElementIsDisplayed(password_TextBox);
	}

}
