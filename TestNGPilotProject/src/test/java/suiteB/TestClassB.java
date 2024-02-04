package suiteB;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestBase.TestBase;
import dataprovider.TestDataProvider;

import org.testng.annotations.Test;

public class TestClassB extends TestBase {
	
	//@Parameters("browser")
	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dataProviderSuiteB", groups = {"sanity"} )
	public void testB(String arg1, String arg2) throws InterruptedException {
		log("Strating TestB");
		log("UserName -- " + arg1);
		log("Password -- " + arg2);
		log("Getting Browser Value -- " + browser);
		//log("Browser is : " + browser);
		Thread.sleep(2000);
		log("Ending TestB");
	}

}
