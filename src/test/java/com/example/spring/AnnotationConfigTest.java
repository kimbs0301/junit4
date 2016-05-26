package com.example.spring;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import junit.extensions.PA;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.example.model.Order;
import com.example.spring.service.SampleService;

/**
 * @author gimbyeongsu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfig.class }, loader = AnnotationConfigContextLoader.class)
public class AnnotationConfigTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationConfigTest.class);
	
	@Autowired
	private SampleService sampleService;

	@BeforeClass
	public static void setUp() {
		LOGGER.debug("-----> SETUP <-----");
	}

	@Test
	public void test_sampleService() {
		assertEquals("class com.example.spring.service.impl.SampleServiceImpl", this.sampleService
				.getClass().toString());
	}

	@Test
	public void test_sampleServiceGetAccountDescription() {
		assertTrue(sampleService.getOrderDescription().contains("Description:"));
	}

	@Test
	public void test_sampleServiceGetAccountCode() {
		assertTrue(sampleService.getOrderStringCode().contains("Code:"));
	}

	@Test
	public void test_sampleServiceCreateNewOrder() {
		Order newOrder = new Order();
		newOrder.setSecurityCode("XYZ");
		newOrder.setDescription("Description");
		if (newOrder != null) {
			assertThat(sampleService.createOrder(newOrder), instanceOf(Order.class));
			assertNotNull("Security isn't null", newOrder.getSecurityCode());
			assertNotNull("Description isn't not null", newOrder.getDescription());
		}

		assertNotNull("New Order is not null", newOrder);
	}

	@Test
	public void test_sampleServiceGetOrder() {
		Order existingOrder = sampleService.getOrder(0);

		if (existingOrder != null) {
			assertThat(sampleService.getOrder(0), instanceOf(Order.class));
			assertNotNull("Security isn't null", existingOrder.getSecurityCode());
			assertNotNull("Description isn't null", existingOrder.getDescription());
		}

		assertNotNull("Object is not null", existingOrder);
	}
	
	@Test
	public void test_private_method() throws Exception {
		PA.invokeMethod(sampleService, "privateTest(java.lang.String)", "TEST");
	}

	@AfterClass
	public static void afterTest() {
		LOGGER.debug("-----> DESTROY <-----");
	}
}
