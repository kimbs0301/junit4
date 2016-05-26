package com.example.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author gimbyeongsu
 * 
 */
@Configuration
public class ApplicationContextJedisConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextJedisConfig.class);
	
	public ApplicationContextJedisConfig() {
		LOGGER.info("ApplicationContextJedisConfig 생성자");
	}

	@Bean(name = "jedisPool")
	public JedisPool getJedisPool() {
		JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
		return jedisPool;
	}
	
	@Bean(name = "appContext")
	public AppContext getAppContext() {
		return new AppContext();
	}
}
