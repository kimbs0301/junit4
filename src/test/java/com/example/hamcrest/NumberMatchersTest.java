package com.example.hamcrest;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author gimbyeongsu
 *
 */
public class NumberMatchersTest {

	@Test
	public void test_number_close_to_number() {
		assertThat(200.24, CoreMatchers.is(closeTo(200, 1)));
	}

	@Test
	public void test_number_greaterthan() {
		assertThat(20, greaterThan(18));
	}

	@Test
	public void test_everyitem_in_list_greaterthan_number() {
		List<Integer> ages = Lists.newArrayList(21, 25, 30);
		assertThat(ages, everyItem(greaterThan(18)));
	}

	@Test
	public void test_everyitem_in_list_greaterthan_or_equal_to_number() {
		List<Integer> ages = Lists.newArrayList(21, 25, 30, 18);
		assertThat(ages, everyItem(greaterThanOrEqualTo(18)));
	}

	@Test
	public void test_everyitem_in_list_lessthan_number() {
		List<Integer> ages = Lists.newArrayList(21, 25, 30);
		assertThat(ages, everyItem(lessThan(31)));
	}

	@Test
	public void test_everyitem_in_list_lessthan_or_equal_to_number() {
		List<Integer> ages = Lists.newArrayList(21, 25, 30, 18);
		assertThat(ages, everyItem(lessThanOrEqualTo(30)));
	}

	@Test
	public void test_bigdecimal_is_close_to_bigdecimal() {
		BigDecimal seniorCitizen = new BigDecimal(65);

		assertThat(new BigDecimal(60),
				CoreMatchers.is(closeTo(seniorCitizen, new BigDecimal(5))));
	}
}
