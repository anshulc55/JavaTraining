package com.daradriver.framework.test.LoginTest;

import java.util.Hashtable;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.datadriven.framework.utils.TestDataProvider;
import com.datadrivern.framework.base.BaseUI;

public class ZohoLoginTest extends BaseUI{
	
	@Test(dataProvider="getDatadoZohoLoginTest")
	public void doZohoLoginTest(Hashtable<String, String> dataTable){
		
		logger = report.createTest("Zoho Login Test Case : " + dataTable.get("Comment"));
		invokeBrowser("Chrome");
		openURL("websiteURL");
		elementClick("zohoLoginTextBox_ClassName");
		enterText("zohoUserNameTextBox_Id", dataTable.get("UserName"));
		enterText("zhPasswordTB_Id", dataTable.get("Password"));
		elementClick("zhSignBtn_Id");
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		waitForPageLoad();
		
		verifyPageTitle(dataTable.get("PageTitle"));
		
	}
	
	@DataProvider
	public Object[][] getDatadoZohoLoginTest(){
		return TestDataProvider.getTestData("ZohoTestData.xlsx", "LoginTest", "doZohoLoginTest");
	}
	
	@AfterTest
	public void endReport() {
	report.flush();
	}

}
