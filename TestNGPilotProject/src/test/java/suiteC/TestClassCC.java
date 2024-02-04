package suiteC;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import TestBase.TestBase;

import org.testng.annotations.Test;

public class TestClassCC extends TestBase {
	
	//@Parameters("browser2")
	@Test(groups = {"sanity", "browser2"})
	public void testCC() throws InterruptedException {
		log("Strating TestCC");
		//log("Executing on Broser : " + browser1);
		Thread.sleep(2000);
		log("Getting Browser Value -- " + browser);
		log("Ending TestCC");
	}

}
