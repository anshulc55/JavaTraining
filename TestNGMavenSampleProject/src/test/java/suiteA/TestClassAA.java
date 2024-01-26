package suiteA;

import org.testng.annotations.Test;

public class TestClassAA {
	
	@Test
	public void testAA() throws InterruptedException {
		System.out.println("Strating TestAA");
		Thread.sleep(5000);
		System.out.println("Ending TestAA");
	}

}
