package suiteC;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestBase.TestBase;


public class TestClassC extends TestBase {
	
	//@Parameters("browser2")
	@Test(groups = {"smoke", "sanity", "browser1"})
	public void testC() throws InterruptedException {
		log("Strating TestC");
		//log("Consuming Paramater : " + arg1);
		Thread.sleep(2000);
		log("Ending TestC");
		log("Getting Browser Value -- " + browser);
	}

}
