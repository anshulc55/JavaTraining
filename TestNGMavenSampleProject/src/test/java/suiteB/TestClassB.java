package suiteB;

import org.testng.annotations.Test;

public class TestClassB {
	
	@Test
	public void testB() throws InterruptedException {
		System.out.println("Strating TestB");
		Thread.sleep(5000);
		System.out.println("Ending TestB");
	}

}
