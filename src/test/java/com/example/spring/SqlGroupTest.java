package com.example.spring;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Player;

/**
 * @author gimbyeongsu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext-sqlGroup.xml" })
@SqlGroup({
		@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema/before.sql"),
		@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:schema/after.sql") })
@Transactional
public class SqlGroupTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(SqlGroupTest.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public class PlayerRowMapper implements RowMapper<Player> {

		@Override
		public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
			Player player = new Player(rs.getInt("player_id"));
			player.setName(rs.getString("name"));
			player.setAge(rs.getInt("age"));
			return player;
		}
	}

	@BeforeClass
	public static void setUp() {
		LOGGER.debug("SETUP");
	}

	@AfterClass
	public static void afterTest() {
		LOGGER.debug("DESTROY");
	}

	@Test
	public void test_query_for_one_player() {
		String sql = "SELECT * FROM `player` WHERE `player_id` = 1";
		Player player = jdbcTemplate.queryForObject(sql, new PlayerRowMapper());
		LOGGER.debug("{}", player);
		assertEquals("홍길동", player.getName());
	}

	@Test
	public void test_query_for_list_players() {
		String sql = "SELECT * FROM `player`";
		List<Player> players = jdbcTemplate.query(sql, new PlayerRowMapper());
		LOGGER.debug("{}", players);
		assertEquals(7, players.size());
	}
}
