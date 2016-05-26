package com.example.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.spring.service.SampleService;
import com.example.spring.service.impl.SampleServiceImpl;

/**
 * @author gimbyeongsu
 * 
 */
@Configuration
public class ApplicationContextConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextConfig.class);
	
	public ApplicationContextConfig() {
		LOGGER.info("ApplicationContextConfig 생성자");
	}
	
	@Bean
	public SampleService getSampleService() {
		return new SampleServiceImpl();
	}
}