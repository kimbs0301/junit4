package com.example.spring.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Player;
import com.example.spring.dao.PlayerDao;
import com.example.spring.service.MockTestService;

/**
 * @author gimbyeongsu
 * 
 */
@Service("mockTestService")
public class MockTestServiceImpl implements MockTestService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MockTestServiceImpl.class);
	
	@Autowired
	private PlayerDao playerDao;

	@Override
	public Player getPlayer() {
		LOGGER.debug("{}", playerDao);
		return playerDao.getPlayer();
	}
}
