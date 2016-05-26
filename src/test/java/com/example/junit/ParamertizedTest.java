package com.example.junit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.util.CalculatorUtils;

/**
 * @author gimbyeongsu
 *
 */
@RunWith(Parameterized.class)
public class ParamertizedTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParamertizedTest.class);
	
	/**
	 * 테스트 파라메타
	 */
	@Parameters
	public static Collection<Object[]> datas() {
		return Arrays.asList(new Object[][] { { 0, 0, 0 }, { 1, 0, 1 },
				{ 0, 1, 1 }, { 1, 1, 2 }, { 1, 2, 3 }, { 2, 1, 3 } });
	}

	private int a;
	private int b;
	private int expected;

	public ParamertizedTest(int a, int b, int expected) {
		this.a = a;
		this.b = b;
		this.expected = expected;
	}

	@Test
	public void test_parameters() {
		LOGGER.debug("a={} b {} = c {}", new Object[] {a, b, expected});

		int calculatedValue = CalculatorUtils.add(a, b);
		assertEquals(expected, calculatedValue);
	}
}
