package com.example.rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
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
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;

import com.example.model.UserRank;
import com.example.spring.AppContext;
import com.example.spring.ApplicationContextJedisConfig;
import com.google.common.collect.Maps;

/**
 * @author gimbyeongsu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextJedisConfig.class }, loader = AnnotationConfigContextLoader.class)
public class RankTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(RankTest.class);

	@Autowired
	private JedisPool jedisPool;

	private static Map<String, Integer> testMemberMap = Maps.newHashMap();
	private static final String KEY = "rank:simple";

	@BeforeClass
	public static void setUp() {
		LOGGER.debug("SETUP");
		testMemberMap.put("user001", 100);
		testMemberMap.put("user002", 98);
		testMemberMap.put("user003", 99);
		testMemberMap.put("user004", 98);
		testMemberMap.put("user005", 95);
	}

	@AfterClass
	public static void afterTest() {
		LOGGER.debug("DESTROY");
		AbstractApplicationContext applicationContext = AppContext.getApplicationContext();
		JedisPool jedisPool = applicationContext.getBean("jedisPool", JedisPool.class);
		String[] arr = new String[testMemberMap.size()];
		testMemberMap.keySet().toArray(arr);
		Jedis jedis = jedisPool.getResource();
		jedis.zrem(KEY, arr);
		jedis.close();
	}

	@Test
	public void test_rank() throws Exception {
		for (String each : testMemberMap.keySet()) {
			setPoint(KEY, each, testMemberMap.get(each));
		}

		for (String each : testMemberMap.keySet()) {
			LOGGER.debug("{}:{}", each, getRank(KEY, each));
		}

		LOGGER.debug("");
		List<UserRank> topRanks = getTopRank(KEY, 0, 100);
		for (UserRank each : topRanks) {
			LOGGER.debug("id:{} rank:{} point:{}", new Object[] { each.getId(), each.getRank(),
					each.getPoint() });
		}
		calculateRank(topRanks, 1);

		LOGGER.debug("");
		for (UserRank each : topRanks) {
			LOGGER.debug("id:{} rank:{} point:{}" + each.getPoint(), new Object[] { each.getId(),
					each.getRank(), each.getPoint() });
		}
	}

	public void setPoint(String key, String member, double point) {
		Jedis jedis = jedisPool.getResource();
		try {
			Transaction trans = jedis.multi();
			trans.zadd(key, point, member);
			trans.expire(key, 10000);
			trans.exec();
		} catch (JedisConnectionException e) {
			LOGGER.error("", e);
		} catch (Exception e) {
			LOGGER.error("", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
	}

	public int getRank(String key, String member) {
		int rank = Integer.MAX_VALUE;
		Jedis jedis = jedisPool.getResource();
		try {
			Double score = jedis.zscore(key, member);
			if (score != null) {
				int point = score.intValue();

				Long count = jedis.zcount(key, new StringBuilder("(").append(point).toString(),
						"+inf");

				rank = count == null ? 0 : count.intValue() + 1;
			}
		} catch (JedisConnectionException e) {
			LOGGER.error("", e);
		} catch (Exception e) {
			LOGGER.error("", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return rank;
	}

	public List<UserRank> getTopRank(String key, int start, int end) {
		List<UserRank> result = new ArrayList<>();
		Jedis jedis = jedisPool.getResource();
		Set<Tuple> topScores = null;
		try {
			topScores = jedis.zrevrangeWithScores(key, start, end);
		} catch (JedisConnectionException e) {
			LOGGER.error("", e);
		} catch (Exception e) {
			LOGGER.error("", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}

		if (topScores != null) {
			for (Tuple each : topScores) {
				UserRank rank = new UserRank();
				result.add(rank);

				rank.setId(each.getElement());
				rank.setPoint((int) each.getScore());
			}
		}

		return result;
	}

	public void close() {
		jedisPool.close();
	}

	public void calculateRank(List<? extends Rankable> sortedRanks, int initRank) {
		int rank = initRank;
		int prevPoint = -1;
		int tieCnt = 0;

		for (Rankable each : sortedRanks) {
			if (tieCnt != 0) {
				if (each.getRankPoint() < prevPoint) {
					rank += tieCnt + 1;
					each.setRank(rank);
					tieCnt = 0;
				}
			} else {
				if (prevPoint == -1) {
					each.setRank(rank);
				} else {
					if (each.getRankPoint() < prevPoint) {
						each.setRank(++rank);
					}
				}
			}

			if (each.getRankPoint() == prevPoint) {
				++tieCnt;
				each.setRank(rank);
			}

			prevPoint = each.getRankPoint();
		}
	}
}