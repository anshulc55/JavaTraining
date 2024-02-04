package suiteA;

import org.testng.annotations.Test;
import org.testng.Assert;
import TestBase.TestBase;
import dataprovider.TestDataProvider;

public class TestClassA extends TestBase {

	@Test(dataProviderClass = TestDataProvider.class, dataProvider = "dataProviderSuiteA", groups = {"smoke"})
	public void testA(String arg1, String arg2) throws InterruptedException {
		log("Staring Test A");
		log("UserName -- " + arg1);
		
		if (!arg1.equals("USERNAME_DEMO")) {
			softAssert("Validation Failure --" + arg1 + " not Equals to USERNAME_DEMO");
		}
		
		//softAssert.assertEquals(arg1, "USERNAME_DEMO");
		//Assert.assertEquals(arg1, "USERNAME_DEMO");
		
		log("Password -- " + arg2);
		if (!arg2.equals("USER_PASSWORD")) {
			failAndStop("Password Doesn't match...");
			//softAssert("Validation Failure --" + arg2 + " not Equals to USER_PASSWORD");
		}
		
		//softAssert.assertEquals(arg2, "USER_PASSWORD");
		if (!arg2.equals("USER_PASSWORD")) {
			softAssert("Validation Failure --" + arg2 + " not Equals to USER_PASSWORD");
		}
		
		Thread.sleep(2000);
		log("Ending TestA");
		
		//Assert.assertEquals(arg1, "USERNAME_DEMO");
		softAssert.assertAll();
	}

}
