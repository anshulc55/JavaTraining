package baseClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PageClasses.LogOutPage;

public class TopMenuClass extends PageBaseClass{
	
	public TopMenuClass(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath="//*[@id='wrapper']/div[3]/ul/li[2]/a")
	public WebElement myPortfolioLink;
	
	@FindBy(xpath="//*[@id='signin_info']/a")
	public WebElement singoutLink;
	
	public LogOutPage singOutApplication(){
		singoutLink.click();
		return PageFactory.initElements(driver, LogOutPage.class);
	}

}
