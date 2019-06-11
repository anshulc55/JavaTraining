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
import utilities.ConstantValue;
import utilities.TestDataProvider;

public class MyPorfolioTest extends BaseTestClass{
	
	LandingPage landingPage;
	MoneyPage moneyPage;
	PortFolioLoginPage portfolioLoginPage;
	MyPorfolioPage myporfolioPage;
	TopMenuClass topMenu;
	
	@Test(dataProvider="getOpenPortfolioTestData", groups={"Regression", "LoginTest" })
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
	
	@Test(dataProvider="verifyLoginTestData", groups={"Regression", "LoginTest" })
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
	
	@Test(dataProvider="addPortfolioTestData", groups={"Regression", "AddPortfolio" })
	public void addPortfolioTest(Hashtable<String, String> testData){
		logger = report.createTest("Create Porfolio Test : " + testData.get("Comment"));
		invokeBrowser("chrome");
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		moneyPage =landingPage.clickMoneyLink();
		portfolioLoginPage = moneyPage.clickSingInLink();
		myporfolioPage = portfolioLoginPage.doLogin(ConstantValue.userName, ConstantValue.password);
		myporfolioPage.verifyMoneyBiz();
		myporfolioPage.clickCreatePortfolio();
		waitForPageLoad();
		myporfolioPage.enterPortfolioName(testData.get("PortfolioName"));
		myporfolioPage = myporfolioPage.submitPortfolio();
		waitForPageLoad();
		myporfolioPage.isPorfolioExists(testData.get("PortfolioName"));
	}
	
	
	@Test(dataProvider="addPortfolioTestData", groups={"Regression", "DeletePortfolio" })
	public void deletePortfolio(Hashtable<String, String> testData){
		logger = report.createTest("Delete Porfolio Test : " + testData.get("Comment"));
		invokeBrowser("chrome");
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingPage = pageBase.OpenApplication();
		moneyPage =landingPage.clickMoneyLink();
		portfolioLoginPage = moneyPage.clickSingInLink();
		myporfolioPage = portfolioLoginPage.doLogin(ConstantValue.userName, ConstantValue.password);
		waitForPageLoad();
		myporfolioPage.verifyMoneyBiz();
		myporfolioPage = myporfolioPage.selectPortfolio(testData.get("PortfolioName"));
		myporfolioPage = myporfolioPage.deletePortFolio();
		waitForPageLoad();
		myporfolioPage.isPorfolioDeleted(testData.get("PortfolioName"));
		
	}
	
	@DataProvider
	public Object[][] getOpenPortfolioTestData(){
		return TestDataProvider.getTestData("RediffPortFolioLaunch.xlsx", "RediffLoginTest", "openPorfolio");
	}
	
	@DataProvider
	public Object[][] verifyLoginTestData(){
		return TestDataProvider.getTestData("RediffPortFolioLaunch.xlsx", "RediffLoginTest", "verifyLogin");
	}
	
	@DataProvider
	public Object[][] addPortfolioTestData(){
		return TestDataProvider.getTestData("RediffPortFolioLaunch.xlsx", "RediffLoginTest", "addPortfolioTest");
	}

}
