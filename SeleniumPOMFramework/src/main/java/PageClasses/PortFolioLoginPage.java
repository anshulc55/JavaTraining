package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class PortFolioLoginPage extends PageBaseClass{
	
	public TopMenuClass topmenu;
	public PortFolioLoginPage(WebDriver driver) {
		this.driver = driver;
		topmenu = PageFactory.initElements(driver, TopMenuClass.class);
	}
	
	@FindBy(id="useremail")
	public WebElement useremail_TextBox;
	
	@FindBy(id="emailsubmit")
	public WebElement emailSubmit_button;
	
	@FindBy(id="userpass")
	public WebElement password_TextBox;
	
	@FindBy(id="loginsubmit")
	public WebElement submitLogin_button;
	
	public MyPorfolioPage doLogin(String userName, String password){
		useremail_TextBox.sendKeys(userName);
		emailSubmit_button.click();
		password_TextBox.sendKeys(password);
		submitLogin_button.click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String currentPageTitle= driver.getTitle();
		if (currentPageTitle.equals("Indian stock markets: Login to Portfolio"))
			Assert.fail("Login Failed");
		return PageFactory.initElements(driver, MyPorfolioPage.class);
		
	}

}
