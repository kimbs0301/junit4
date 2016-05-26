package com.example.junit;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author gimbyeongsu
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpecifyOrderOfTest {
	private static String executionOrder = "";

	@Test
	public void c_three() {
		executionOrder += "three";
	}

	@Test
	public void a_one() {
		executionOrder += "one";
	}

	@Test
	public void b_two() {
		executionOrder += "two";
	}

	@AfterClass
	public static void validate() {
		assertEquals("onetwothree", executionOrder);
	}
}
