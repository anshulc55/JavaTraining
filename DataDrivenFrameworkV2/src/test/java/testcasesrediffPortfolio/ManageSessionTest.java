package testcasesrediffPortfolio;


import org.testng.ITestContext;
import org.testng.annotations.Test;
import testbase.BaseTest;

public class ManageSessionTest extends BaseTest{
	
	@Test
	public void doLogin(ITestContext context) throws InterruptedException {
		app.logInfo("Login Application");
		
		app.openBrowser("browser_name");
		app.openURL("URL");
		app.click("signIn_linkText");
		app.waitforWebPageToLoad();
		app.type("userName_id", "anshulc55@rediffmail.com");
		app.type("password_xpath", "Test@12345");
		//app.enterCaptcha("captcha_id");
		app.wait(30);
		app.clickButton("submitBtn_id");
	}

	
	@Test
	public void doLogOut(ITestContext context) {
		System.out.println("***** LogOut Application ******");
		//ApplicationKeywords app = new ApplicationKeywords();
	}
}
