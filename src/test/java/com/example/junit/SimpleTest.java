package com.example.junit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.example.util.CalculatorUtils;

/**
 * @author gimbyeongsu
 * 
 */
public class SimpleTest {

	@Test
	public void test_equals() {
		int calculatedValue = CalculatorUtils.add(5, 10);
		assertEquals(15, calculatedValue);
	}

	@Test
	public void test_boolean() {
		boolean a = true;
		boolean b = false;
		assertTrue(a);
		assertFalse(b);
	}
	
	@Test
	public void test_not_null() throws Exception {
		Object obj = new Object();
		assertNotNull(obj);
	}
	
	@Test
	public void test_not_same() throws Exception {
		Object obj1 = new Object();
		Object obj2 = new Object();
		assertNotSame(obj1, obj2);
	}
	
	@Test
	public void test_not_equals() throws Exception {
		assertNotEquals(1, 2);
		// assertNotEquals(1, 1); // AssertionError
		Object obj1 = new Object();
		Object obj2 = new Object();
		assertNotEquals(obj1, obj2);
		// assertNotEquals(obj1, obj1); // AssertionError
	}

	@Test
	public void test_array_equals() throws Exception {
		assertThat(new Integer[] { 1, 2, 3, 4, 5 }, equalTo(new Integer[] { 1, 2, 3, 4, 5 }));
		assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5 }, new Integer[] { 1, 2, 3, 4, 5 });
	}
	
	@Test
	public void test_array_not() throws Exception {
		assertThat(new Integer[] { 1, 2, 3, 4, 5 }, not(new Integer[] { 1, 2 }));
	}
}
