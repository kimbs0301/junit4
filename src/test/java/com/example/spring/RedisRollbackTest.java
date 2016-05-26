package com.example.spring;

import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author gimbyeongsu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextJedisConfig.class }, loader = AnnotationConfigContextLoader.class)
public class RedisRollbackTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisRollbackTest.class);
	
	@Autowired
	private JedisPool jedisPool;
	
	private static final Set<String> rollbackSet = Sets.newHashSet();
	private static final List<KeyMember> rollbackList = Lists.newArrayList();
	
	private static class RollbackConfig {
		public static final boolean IS_ROLLBACK = false;
	}
	
	private static class KeyMember {
		private String key;
		private String member;
		
		public KeyMember(String key, String member) {
			this.key = key;
			this.member = member;
		}
		
		public String getKey() {
			return key;
		}
		
		public String getMember() {
			return member;
		}
	}
	
	@AfterClass
	public static void afterTest() {
		LOGGER.debug("DESTROY");
	}
	
	@After
	public void shutdown() {
		if (!RollbackConfig.IS_ROLLBACK) {
			LOGGER.info("ROLLBACK");
			AbstractApplicationContext applicationContext = AppContext.getApplicationContext();
			JedisPool jedisPool = applicationContext.getBean("jedisPool", JedisPool.class);
			Jedis jedis = jedisPool.getResource();
			for (String key : rollbackSet) {
				String val = jedis.get(key);
				LOGGER.debug("del:{}", val);
				jedis.del(key);
			}
			for (KeyMember each : rollbackList) {
				jedis.zrem(each.getKey(), each.getMember());
			}
			jedis.close();
		}
	}
	
	@Test
	public void test_set() throws Exception {
		String key = "qwerrt";
		String value = "123";
		Jedis jedis = jedisPool.getResource();
		try {
			rollbackSet.add(key);
			jedis.set(key, value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	@Test
	public void test_zadd() throws Exception {
		String key = "aaa";
		String member = "bbb";
		double point = 1000D;
		Jedis jedis = jedisPool.getResource();
		try {
			rollbackList.add(new KeyMember(key, member));
			jedis.zadd(key, point, member);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
