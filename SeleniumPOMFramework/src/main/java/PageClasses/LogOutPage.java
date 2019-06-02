package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class LogOutPage extends PageBaseClass{
	
	public TopMenuClass topmenu;
	
	public LogOutPage(WebDriver driver) {
		this.driver = driver;
		topmenu = PageFactory.initElements(driver, TopMenuClass.class);
	}
	
	
	@FindBy(xpath="//*[@id='wrapper']/div[4]/a")
	public WebElement loginAgainLink;
	
	public LoginPage clickLoginAgain(){
		loginAgainLink.click();
		return PageFactory.initElements(driver, LoginPage.class);
	}

}
