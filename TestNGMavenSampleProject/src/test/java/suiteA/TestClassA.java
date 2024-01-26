package suiteA;

import org.testng.annotations.Test;

public class TestClassA {
	
	@Test
	public void testA() throws InterruptedException {
		System.out.println("Strating TestA");
		Thread.sleep(5000);
		System.out.println("Ending TestA");
	}

}
