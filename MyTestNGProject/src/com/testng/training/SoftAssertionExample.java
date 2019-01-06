package com.testng.training;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertionExample {

	@Test
	public void testCaseOne() {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("*** test case one started ***");
		softAssert.assertEquals(5, 5, "First hard assert failed");
		System.out.println("Soft assert success....");
		softAssert.assertTrue("Hello".equals("hello"), "Second hard assert failed");
		System.out.println("*** test case one executed successfully ***");
		softAssert.assertAll();
	}

	@Test
	public void testCasetwo() {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("*** test case two started ***");
		softAssert.assertEquals("Hello", "Hello", "First soft assert failed");
		System.out.println("Soft assert success....");
		softAssert.assertTrue(false, "Second soft assert failed");
		System.out.println("*** test case two executed successfully ***");
		softAssert.assertAll();
	}
	
	@Test
	public void testCaseThree() {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("*** test case Three started ***");
		softAssert.assertEquals(5, 5, "First hard assert failed");
		System.out.println("hard assert success....");
		Assert.assertTrue("Hello".equals("Hello"), "Second hard assert failed");
		System.out.println("*** test case Three executed successfully ***");
	}
	

}
