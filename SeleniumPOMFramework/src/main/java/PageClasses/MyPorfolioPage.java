package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class MyPorfolioPage extends PageBaseClass{
	
	public TopMenuClass topmenu;
	
	public MyPorfolioPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topmenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topmenu);
	}
	
	@FindBy(xpath="//*[@id='headcontent']/div[1]/div[1]/a/span")
	public WebElement moneyBiz_text;
	
	public void verifyMoneyBiz(){
			moneyBiz_text.isDisplayed();
	}
	
	public TopMenuClass gettopMenu(){
		return topmenu;
	}
	
	

}
