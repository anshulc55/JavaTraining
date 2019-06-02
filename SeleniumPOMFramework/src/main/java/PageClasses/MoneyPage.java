package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class MoneyPage extends PageBaseClass{
	
	public TopMenuClass topmenu;
	
	public MoneyPage(WebDriver driver) {
		this.driver = driver;
		topmenu = PageFactory.initElements(driver, TopMenuClass.class);
	}

 @FindBy(xpath="//*[@id='signin_info']/a[1]")
 public WebElement signinLink;
 
 public PortFolioLoginPage clickSingInLink(){
	 signinLink.click();
	 return PageFactory.initElements(driver, PortFolioLoginPage.class);
 }
	
}
