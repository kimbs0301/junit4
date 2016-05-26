package com.example.junit;

import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * @author gimbyeongsu
 * 
 */
public class TimeoutTest {

	@Ignore
	@Test(timeout = 100)
	public void test_limit_execution_time() {

		// Infinite loop
		while (true) {
		}
	}
	
	@Rule
	public Timeout globalTimeout = new Timeout(100, TimeUnit.MILLISECONDS);

	@Ignore
	@Test
	public void test_infinite_while_loop() {
		while (true) {
		}
	}
}
