package com.testng.training;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestParameters {
	
	@Test
	@Parameters("browser")
	public void testCaseOne(String browser) {
		System.out.println("browser passed as :- " + browser);
	}

	@Test
	@Parameters({"userName", "password"})
	public void testCaseTwo(String userName, String password) {
		System.out.println("Parameter for User Name passed as :- " + userName);
		System.out.println("Parameter for Password passed as :- " + password);
	}

}
