package ExtentReport;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportUsingConfig {
	
	@Test
	public void sampleReportJSONConfig() throws IOException {
		
		ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("my_JSON_report.html");
        final File CONF = new File("extentConfig.json");
        spark.loadJSONConfig(CONF);

        extent.attachReporter(spark);


        ExtentTest loginTest = extent.createTest("Facebook Login Test");
        loginTest.info("Passing User Name");
        loginTest.info("Passing Password");
        loginTest.info("Click Submit Button");
        loginTest.pass("Test Success....");

        
        ExtentTest homePageTest = extent.createTest("Facebook HomePage Test");
        homePageTest.pass("Enter to FaceBook HomePage");
        homePageTest.info("Click on Profile.");
        homePageTest.fail("Test Failed....");

        extent.flush();
		
	}
	
	@Test
	public void sampleReport() throws IOException {
		
		ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("my_XML_report.html");
        final File CONF = new File("extentConfig.xml");
        spark.loadXMLConfig(CONF);

        extent.attachReporter(spark);


        ExtentTest loginTest = extent.createTest("Facebook Login Test");
        loginTest.info("Passing User Name");
        loginTest.info("Passing Password");
        loginTest.info("Click Submit Button");
        loginTest.pass("Test Success....");

        
        ExtentTest homePageTest = extent.createTest("Facebook HomePage Test");
        homePageTest.pass("Enter to FaceBook HomePage");
        homePageTest.info("Click on Profile.");
        homePageTest.fail("Test Failed....");

        extent.flush();
		
	}
}
