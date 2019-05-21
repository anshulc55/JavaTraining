package com.daradriver.framework.test.LoginTest;

import java.util.Hashtable;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.datadriven.framework.utils.TestDataProvider;
import com.datadrivern.framework.base.BaseUI;

public class loginTest extends BaseUI {

	@Test(dataProvider="getTestOneData")
	public void testOne(Hashtable<String, String> dataTable) {
		
	    logger = report.createTest("Enter UserName And Password in Rediff : " + dataTable.get("Col1 "));
		invokeBrowser("chrome");
		openURL("websiteURL");
		elementClick("singinBtn_Xpath");
		enterText("usrnameTextbox_Id", dataTable.get("Col1 "));
		enterText("passwordTextb0x_CSS", dataTable.get("Col3"));		
	}

	@AfterTest
	public void endReport() {
	report.flush();

	}
	
	@DataProvider
	public Object[][] getTestOneData(){
		return TestDataProvider.getTestData("TestData_Testmanagement.xlsx", "Feature 1", "Test Two");
	}

	 //@Test
	public void testTwo() {
		logger = report.createTest("Open Rediff and Enter UserName");
		invokeBrowser("chrome");
		openURL("websiteURL");
		elementClick("singinBtn_Xpath");
		enterText("usrnameTextbox_Xpath", "anshulc55");
	}

	// @Test(dependsOnMethods="testTwo")
	public void testThree() {
		invokeBrowser("Mozila");
		openURL("websiteURL");
		elementClick("singinBtn_xpath");
		enterText("usrnameTextbox_Xpath", "anshulc55");
		tearDown();

	}

}
