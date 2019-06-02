package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class MyPorfolioPage extends PageBaseClass{
	
	public TopMenuClass topmenu;
	
	public MyPorfolioPage(WebDriver driver) {
		this.driver = driver;
		topmenu = PageFactory.initElements(driver, TopMenuClass.class);
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
