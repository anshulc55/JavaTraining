package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class LandingPage extends PageBaseClass{
	
	
	
	 public LandingPage(WebDriver driver) {
		this.driver = driver;
	}
	 
	
	/*
	 * All WebElements of Landing Page and Associated Operations
	 */
	
//	public void openBrowser(){
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
//		 driver = new ChromeDriver();
//	}
	
//	public void openWebSite(){
//		driver.get("https://www.rediff.com/");
//	}
	
	@FindBy(xpath="//*[contains(@class,'moneyicon')]")
	public WebElement moneyLink;
	
//	public void OpenApplication(){
//		invokeBrowser("Chrome");
//		driver.get("https://www.rediff.com/");
//	}
//	
	public MoneyPage clickMoneyLink(){
		moneyLink.click();
		return PageFactory.initElements(driver, MoneyPage.class);
	}
	
	public LoginPage clickSingIn(){
		//Perform the Click
		return PageFactory.initElements(driver, LoginPage.class);
	}
	//Open Browser Code
	//WebElements 
	//Operations on WebElements
	
}
