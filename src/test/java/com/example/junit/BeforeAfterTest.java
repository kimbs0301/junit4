package com.example.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gimbyeongsu
 * 
 */
public class BeforeAfterTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(BeforeAfterTest.class);

	@Before
	public void setUp() {
		LOGGER.info("start");
	}

	@After
	public void shutdown() {
		LOGGER.info("end");
	}

	@Test
	public void test_one() throws Exception {
		LOGGER.info("test_one");
	}

	@Test
	public void test_two() throws Exception {
		LOGGER.info("test_two");
	}
}
