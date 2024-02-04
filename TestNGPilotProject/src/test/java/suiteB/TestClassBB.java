package suiteB;

import org.testng.annotations.Test;

import TestBase.TestBase;
import dataprovider.TestDataProvider;

import org.testng.annotations.Test;

public class TestClassBB extends TestBase {
	
	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dataProviderSuiteB", groups = {"sanity"} )
	public void testBB(String arg1, String arg2) throws InterruptedException {
		log("Strating TestBB");
		log("UserName -- " + arg1);
		log("Password -- " + arg2);
		Thread.sleep(2000);
		log("Ending TestBB");
	}

}
