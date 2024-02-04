package TestBase;

import java.util.Iterator;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import reports.ExtentReportManager;

public class TestBase {
	
	public ExtentReports report;
	public ExtentTest test; 
	public SoftAssert softAssert;
	public String browser;
	
	
	@BeforeMethod (alwaysRun = true)
	public void init(ITestResult res, ITestContext context) {
		System.out.println("-------@BeforeMethod--------");
		report = ExtentReportManager.getReporter();
		test = report.createTest(res.getMethod().getMethodName().toUpperCase());
		res.setAttribute("reporterObject", test);
		softAssert = new SoftAssert();
		
		// Initialize Browser Variable 
		
		//browser = context.getCurrentXmlTest().getParameter("browser1");
		//System.out.println("Browser Param Value -- " + browser);
		
		//Read the Param Dynamically on basis of associated Group
		String groupNames[] = context.getAllTestMethods()[0].getGroups();
		String browserGroup="";
		for(String g :groupNames ) {
			if(g.startsWith("browser")) {
				browserGroup = g;
				break;
			}
		}
	
		
		browser =  context.getCurrentXmlTest().getParameter(browserGroup);
		System.out.println("Browser is "+ browser);
	}

	@AfterMethod (alwaysRun = true)
	public void quit() {
		//System.out.println("@AfterMethod -- @AfterMethod");
		report.flush();
	}
	
	// Print the log in Extent Report
	public void log(String msg) {
		System.out.println(msg);
		test.info(msg);
	}
	
	public void pass(String msg) {
		System.out.println(msg);
		test.pass(msg);
	}
	
	public void fail(String msg) {
		System.out.println(msg);
		test.fail(msg);
	}
	
	public void skip(String msg) {
		System.out.println(msg);
		test.skip(msg);
	}
	
	// Print SoftAssert error in Extent Report, Fail the Test in TestNG
	public void softAssert(String msg) {
		fail(msg);
		softAssert.fail(msg);
	}
	
	//Print SoftAssert error in Extent and Stop the Test Execution
	public void failAndStop(String msg) {
		fail(msg);
		softAssert.assertAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
