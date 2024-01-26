package suiteB;

import org.testng.annotations.Test;

public class TestClassBB {
	
	@Test
	public void testBB() throws InterruptedException {
		System.out.println("Strating TestBB");
		Thread.sleep(5000);
		System.out.println("Ending TestBB");
	}

}
