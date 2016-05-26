package com.example.junit;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author gimbyeongsu
 * 
 */
public class ExceptionTest {

	/**
	 * 실패 메시지 출력
	 */
	@Ignore
	@Test
	public void test_fail_message() throws Exception {
		fail("에러 메시지");
	}
	
	/**
	 * IndexOutOfBoundsException 예외가 발생
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void test_check_for_indexoutofbounds_exception() {
		new ArrayList<String>().get(0);
	}

	/**
	 * 예외 try catch
	 */
	@Test
	public void test_for_exception_try_catch() {
		try {
			new ArrayList<String>().get(0);
			fail("Expected an IndexOutOfBoundsException to be thrown");
		} catch (IndexOutOfBoundsException e) {
			assertThat(e.getMessage(), isA(String.class));
			// assertThat(e.getMessage(), org.hamcrest.CoreMatchers.is(org.hamcrest.CoreMatchers
			//		.instanceOf(String.class)));
		}
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * 예외 룰 적용
	 */
	@Test
	public void test_for_exception_with_rule() throws IndexOutOfBoundsException {
		List<String> list = new ArrayList<>();

		thrown.expect(IndexOutOfBoundsException.class);
		thrown.expectMessage("Index: 0, Size: 0");
		list.get(0);
	}
}