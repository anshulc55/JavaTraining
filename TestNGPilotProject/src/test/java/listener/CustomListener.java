package listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CustomListener implements ITestListener{
	
	public void onTestFailure(ITestResult result) {
		System.out.println("******* Test FAILURE *******");
//		System.out.println("Test Name : " + result.getName());
//		System.out.println("Error : " + result.getThrowable().getMessage());
//		System.out.println("Attribute Value : " + result.getAttribute("Author") );
		ExtentTest test = (ExtentTest) result.getAttribute("reporterObject");
		test.log(Status.INFO, "Test Case Name : " + result.getName());
		test.fail("Failure : " + result.getThrowable().getMessage());
		
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("****** Test SUCCESS *******");
		ExtentTest test = (ExtentTest) result.getAttribute("reporterObject");
		test.log(Status.INFO, "Test Case Name : " + result.getName());
		test.pass("Test Status is Success");
	}
}