package ExtentReport;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

// 1. Create a Report of All Test Cases
// 2. Create a Report of only failed Test Cases

public class FailedTestsReport {

	@Test
	public void ReportofFailedTests() {

		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("fullreport-index.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setReportName("Success Tests Report");
		spark.config().setDocumentTitle("Full Test Suite Report");

		ExtentSparkReporter failedTestspark = new ExtentSparkReporter("failedTests-index.html").filter().statusFilter()
				.as(new Status[] { Status.FAIL, Status.SKIP }).apply();

		failedTestspark.config().setTheme(Theme.DARK);
		failedTestspark.config().setReportName("Only Failed Tests Report");
		failedTestspark.config().setDocumentTitle("Failed Tests Report");

		extent.attachReporter(spark, failedTestspark);

		ExtentTest loginTest = extent.createTest("Facebook Login Test with Valid Creds");
		loginTest.info("Passing User Name");
		loginTest.info("Passing Correct Password");
		loginTest.info("Click Submit Button");
		loginTest.pass("Test Success....");

		ExtentTest homePageTest = extent.createTest("Facebook HomePage Test");
		homePageTest.pass("Enter to FaceBook HomePage");
		homePageTest.info("Click on Profile.");
		homePageTest.fail("Test Failed....");

		ExtentTest loginTestFailed = extent.createTest("Facebook Login Test with Invalid Creds");
		loginTestFailed.info("Passing User Name");
		loginTestFailed.info("Passing Incorrect Password");
		loginTestFailed.info("Click Submit Button");
		loginTestFailed.fail("Test Success....");

		ExtentTest profilePageTest = extent.createTest("Facebook Profile Page Test");
		profilePageTest.pass("Enter to FaceBook Profile Page");
		profilePageTest.info("Click on Profile.");
		profilePageTest.pass("Test Success....");
		
		ExtentTest timelinePageTest = extent.createTest("Facebook Timeline Page Test");
		timelinePageTest.pass("Enter to FaceBook Timeline Page");
		timelinePageTest.info("Click on Profile.");
		timelinePageTest.skip("Test Skip....");

		extent.flush();

	}

}
