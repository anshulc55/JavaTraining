package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class LoginPage extends PageBaseClass {

	public TopMenuClass topmenu;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		topmenu = PageFactory.initElements(driver, TopMenuClass.class);
	}

	// WebElements on Login Page
	// Functions associated with Login Page

	public RediffMailPage doLogin() {
		// Perform the Login

		// If login Successful
		return PageFactory.initElements(driver, RediffMailPage.class);
		// else
		// return new LoginPage();
	}
	
	public TopMenuClass getTopmenu(){
		return topmenu;
	}

	public void getTitle() {

	}
}
