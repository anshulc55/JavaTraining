package TestCases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import PageClasses.LandingPage;
import PageClasses.MoneyPage;
import PageClasses.MyPorfolioPage;
import PageClasses.PortFolioLoginPage;
import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;
import utilities.TestDataProvider;

public class MyPorfolioTest extends BaseTestClass{
	
	LandingPage landingPage;
	MoneyPage moneyPage;
	PortFolioLoginPage portfolioLoginPage;
	MyPorfolioPage myporfolioPage;
	TopMenuClass topMenu;
	
	@Test(dataProvider="getOpenPortfolioTestData")
	public void openPorfolio(Hashtable<String, String> testData){
		logger = report.createTest("Login Rediff Portfolio : " + testData.get("Comment"));
		
		invokeBrowser("chrome");
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		moneyPage =landingPage.clickMoneyLink();
		portfolioLoginPage = moneyPage.clickSingInLink();
		myporfolioPage = portfolioLoginPage.doLogin(testData.get("UserName"), testData.get("Password"));
		myporfolioPage.verifyMoneyBiz();
		myporfolioPage.getTitle(testData.get("PageTitle"));
		topMenu = myporfolioPage.gettopMenu();
		topMenu.singOutApplication();
	}
	
	@Test(dataProvider="verifyLoginTestData")
	public void verifyLogin(Hashtable<String, String> testData){
		logger = report.createTest("Login Rediff Portfolio : " + testData.get("Comment"));
		
		invokeBrowser("chrome");
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		moneyPage =landingPage.clickMoneyLink();
		portfolioLoginPage = moneyPage.clickSingInLink();
		portfolioLoginPage.enterUser(testData.get("UserName"));
		portfolioLoginPage.submitUserName();
		portfolioLoginPage.verifyPasswordField();
		
	}
	
	@DataProvider
	public Object[][] getOpenPortfolioTestData(){
		return TestDataProvider.getTestData("RediffPortFolioLaunch.xlsx", "RediffLoginTest", "openPorfolio");
	}
	
	@DataProvider
	public Object[][] verifyLoginTestData(){
		return TestDataProvider.getTestData("RediffPortFolioLaunch.xlsx", "RediffLoginTest", "verifyLogin");
	}

}
