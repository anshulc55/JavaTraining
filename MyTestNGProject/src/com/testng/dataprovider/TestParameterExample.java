package com.testng.dataprovider;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestParameterExample {
	
	@Test (dataProvider = "DoubleSet")
	public void testAddition(int actual, int expected){
		int actualValue = actual + 20;
		Assert.assertEquals(actualValue, expected);
	}
	
	
	@DataProvider
	public Object[][] getData(){
		return new Object[][]{
			{100, 120},
			{200, 220},
			{250, 270},
			{300, 310},
			{400, 420}
		};
	}
	
	@DataProvider (name = "DoubleSet")
	public Object[][] getDataSet2(){
		return new Object[][]{
			{10, 30},
			{20, 40},
		};
	}
}
