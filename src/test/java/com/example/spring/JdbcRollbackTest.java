package com.example.spring;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.annotation.Rollback;
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
public class JdbcRollbackTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(JdbcRollbackTest.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static class RollbackConfig {
		public static final boolean IS_ROLLBACK = false;
	}

	@Test
	@Rollback(RollbackConfig.IS_ROLLBACK)
	public void test_insert() throws Exception {
		String sql = "INSERT INTO `player`(`name`, `age`) VALUES (?,?)";
		jdbcTemplate.update(sql, new Object[] { "테스트", 99 }, new int[] { Types.VARCHAR,
				Types.INTEGER });
	}

	@Test
	@Rollback(RollbackConfig.IS_ROLLBACK)
	public void test_update() throws Exception {
		jdbcTemplate.update("UPDATE `player` SET `age` = ? WHERE `player_id` = 1", new Object[] { 99 },
				new int[] { Types.INTEGER });

		String sql = "SELECT * FROM `player` WHERE `player_id` = 1";
		Player player = jdbcTemplate.queryForObject(sql, new RowMapper<Player>() {

			@Override
			public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
				Player player = new Player(rs.getInt("player_id"));
				player.setName(rs.getString("name"));
				player.setAge(rs.getInt("age"));
				return player;
			}
		});

		LOGGER.debug("age:{}", player.getAge());
		assertEquals(player.getAge(), 99);
	}
}
