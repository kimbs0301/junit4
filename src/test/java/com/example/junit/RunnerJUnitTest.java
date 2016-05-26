package com.example.junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnerJUnitTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(RunnerJUnitTest.class);
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(SimpleTest.class);
		for (Failure failure : result.getFailures()) {
			LOGGER.debug(failure.toString());
		}
		if (result.wasSuccessful()) {
			LOGGER.debug("테스트 성공");
		}
	}
}
