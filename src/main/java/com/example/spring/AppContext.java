package com.example.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * @author gimbyeongsu
 * 
 */
public class AppContext implements ApplicationContextAware {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppContext.class);
	private static AbstractApplicationContext applicationContext;

	public AppContext() {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		LOGGER.debug("");
		AppContext.applicationContext = (AbstractApplicationContext) applicationContext;
	}

	public static AbstractApplicationContext getApplicationContext() {
		return AppContext.applicationContext;
	}
}