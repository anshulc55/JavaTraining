package com.testng.training;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class SkippingExample {
	
	@Test
	public void testOne(){
		System.out.println("Hi, I am Test One .....");
	}
	
	@Test (enabled=false)
	public void testTwo(){
		System.out.println("Hi, I am Test Two .....");
	}
	
	@Test
	public void testThree(){
		System.out.println("Hi, I am Test Three .....");
		
		throw new SkipException("Skipping Delibretly, As this test is incomplete");
	}
	
	@Test
	public void testFour(){
		System.out.println("Hi, I am Test Four .....");
	}
	

}
