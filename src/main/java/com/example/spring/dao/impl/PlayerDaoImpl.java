package com.example.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.model.Player;
import com.example.spring.dao.PlayerDao;

/**
 * @author gimbyeongsu
 * 
 */
@Repository("playerDao")
public class PlayerDaoImpl implements PlayerDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Player getPlayer() {
		LOGGER.debug("");
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
		return player;
	}
}
