package TestNGExtent;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import reports.ExtentReportManager;

public class RegistrationTest {
	
	ExtentReports extent;
	ExtentTest loginTest;

	@BeforeMethod
	public void init() {
		extent = ExtentReportManager.getReporter();

		loginTest = extent.createTest("Facebook Registration Test");
	}

	@AfterMethod
	public void quit() {
		extent.flush();
	}

	@Test
	public void RegisTest() {
		loginTest.info("Open the FaceBook URL");
		loginTest.info("Enter the Registration Details");
		loginTest.info("Submit the Registration Form");
		loginTest.info("Verify Page Title");
		loginTest.pass("Test Success....");
	}

}
