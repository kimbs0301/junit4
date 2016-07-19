package com.example.spring;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Player;
import com.example.spring.dao.PlayerDao;
import com.example.spring.service.MockTestService;

/**
 * @author gimbyeongsu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext-sqlGroup.xml" })
@SqlGroup({ @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema/before.sql"),
		@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:schema/after.sql") })
@Transactional
public class MockTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(MockTest.class);

	@Mock
	private PlayerDao playerDao;

	@InjectMocks
	@Autowired
	private MockTestService mockTestService;

	@Test
	public void test() throws Exception {
		LOGGER.debug("");
		MockitoAnnotations.initMocks(this);
		when(playerDao.getPlayer()).thenReturn(new Player());

		Player player = mockTestService.getPlayer();
		assertEquals(player.getPlayerId(), 0);
	}
}
