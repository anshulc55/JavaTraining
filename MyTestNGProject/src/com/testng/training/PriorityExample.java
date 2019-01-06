package com.testng.training;

import org.testng.annotations.Test;

public class PriorityExample {
	
	@Test
	public void testOne(){
		System.out.println("Hi, I am Test One .....");
	}
	
	@Test (priority =1)
	public void testTwo(){
		System.out.println("Hi, I am Test Two .....");
	}
	
	@Test (priority =2)
	public void testThree(){
		System.out.println("Hi, I am Test Three .....");
		
	}
	
	@Test (priority =3)
	public void testFour(){
		System.out.println("Hi, I am Test Four .....");
	}
	

}
