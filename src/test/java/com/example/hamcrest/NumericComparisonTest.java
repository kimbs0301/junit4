package com.example.hamcrest;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author gimbyeongsu
 *
 */
public class NumericComparisonTest {

	@Test
	public void test_greaterThan() {
		// >
		assertThat(2, greaterThan(1));
		// assertThat(1, greaterThan(1)); // AssertionError
	}

	@Test
	public void test_greaterThanOrEqualTo() {
		// >=
		assertThat(2, greaterThanOrEqualTo(1));
		assertThat(1, greaterThanOrEqualTo(1));
		// assertThat(0, greaterThanOrEqualTo(1)); // AssertionError
	}

	@Test
	public void test_lessThan() {
		// <
		assertThat(0, lessThan(2));
		assertThat(1, lessThan(2));
		// assertThat(2, lessThan(2)); // AssertionError
	}

	@Test
	public void test_lessThanOrEqualTo() {
		// <=
		assertThat(1, lessThanOrEqualTo(2));
		assertThat(2, lessThanOrEqualTo(2));
		// assertThat(3, lessThanOrEqualTo(2)); // AssertionError
	}
}
