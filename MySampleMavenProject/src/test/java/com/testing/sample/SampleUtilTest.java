package com.testing.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class SampleUtilTest {

	@Test
	public void testAdd() {
		System.out.println("add");
		Integer a = 15;
		Integer b = 15;
		Integer expResult = 30;
		Integer result = SampleUtil.add(a, b);
		assertEquals(expResult, result);
		
		assertEquals(null, SampleUtil.add(null, 15));
		assertEquals(null, SampleUtil.add(null, null));
	}
	
	@Test
	public void testadd1()
	{
		assertEquals(null, SampleUtil.add(15, null));
	}
	
	@Test
	public void testadd2()
	{
		assertEquals(null, SampleUtil.add(null, 15));
	}
	
	@Test
	public void testadd3()
	{
		assertEquals(null, SampleUtil.add(null, null));
	}
}
