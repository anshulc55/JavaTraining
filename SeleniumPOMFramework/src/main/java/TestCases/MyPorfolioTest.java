package TestCases;

import org.testng.annotations.Test;

import PageClasses.LandingPage;
import PageClasses.MoneyPage;
import PageClasses.MyPorfolioPage;
import PageClasses.PortFolioLoginPage;
import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class MyPorfolioTest {
	
	LandingPage landingPage;
	MoneyPage moneyPage;
	PortFolioLoginPage portfolioLoginPage;
	MyPorfolioPage myporfolioPage;
	TopMenuClass topMenu;
	
	@Test
	public void openPorfolio(){
		PageBaseClass pageBase = new PageBaseClass();
		pageBase.invokeBrowser("chrome");
		landingPage = pageBase.OpenApplication();
		moneyPage =landingPage.clickMoneyLink();
		portfolioLoginPage = moneyPage.clickSingInLink();
		myporfolioPage = portfolioLoginPage.doLogin("anshulc55", "Test@1234");
		myporfolioPage.verifyMoneyBiz();
		myporfolioPage.getTitle("Rediff Moneywiz | My Portfolio(s)");	
		topMenu = myporfolioPage.gettopMenu();
		topMenu.singOutApplication();
		
	}

}
