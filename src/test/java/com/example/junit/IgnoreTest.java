package com.example.junit;

import static org.junit.Assert.assertFalse;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author gimbyeongsu
 * 
 */
public class IgnoreTest {

	@Ignore("Ignore this test")
	@Test
	public void test_ignore() {
		assertFalse(true); // AssertionError
	}
}
