package testbase;

import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import keywords.ApplicationKeywords;
import reports.ExtentManager;

public class BaseTest {
	public ApplicationKeywords app;
	public String number;
	public ExtentReports extentReport;
	public ExtentTest extentTest;

	@BeforeTest(alwaysRun = true)
	public void beforeTest(ITestContext context) {
		// System.out.println("***** Before Test *******");
		// Single App Object for Single Test
		// Initialize and Share for All the test Cases
		app = new ApplicationKeywords();
		context.setAttribute("app", app);

		// Init Reports
		extentReport = ExtentManager.getReports();
		extentTest = extentReport.createTest(context.getCurrentXmlTest().getName());
		extentTest.log(Status.INFO, "Starting Test : " + context.getCurrentXmlTest().getName());
		app.setReport(extentTest);

		context.setAttribute("extentReport", extentReport);
		context.setAttribute("extentTest", extentTest);

	}

	@AfterTest(alwaysRun = true)
	public void afterTest(ITestContext context) {
		// System.out.println("***** After Test *******");
		app = (ApplicationKeywords) context.getAttribute("app");
		if (app != null) {
			app.quitDriver();
		}

		extentReport = (ExtentReports) context.getAttribute("extentReport");
		if (extentReport != null) {
			extentReport.flush();
		}

	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context) {
		// System.out.println("***** Before Method *******");
		app = (ApplicationKeywords) context.getAttribute("app");
		extentReport = (ExtentReports) context.getAttribute("extentReport");
		extentTest = (ExtentTest) context.getAttribute("extentTest");
		
		String criticalFailure = (String) context.getAttribute("isCriticalFailure");
		if (criticalFailure!=null && criticalFailure.equals("true")) {
			app.logSkip("Critical Failure in Prevoius Test Method");
			throw new SkipException("Critical Failure in Prevoius Test Method");
		}
		
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestContext context) {
		app.reportAll();
	}

}
