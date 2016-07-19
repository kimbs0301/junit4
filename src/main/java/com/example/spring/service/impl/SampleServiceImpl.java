package com.example.spring.service.impl;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.Order;
import com.example.spring.service.SampleService;

/**
 * @author gimbyeongsu
 * 
 */
public class SampleServiceImpl implements SampleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleServiceImpl.class);

	public SampleServiceImpl() {
		privateTest("");
	}

	@Override
	public String getOrderDescription() {
		Order existingOrder = new Order();
		existingOrder.setDescription("Order for XYZ in units");
		return "Description: " + existingOrder.getDescription();
	}

	@Override
	public String getOrderStringCode() {
		Order existingOrder = new Order();
		existingOrder.setSecurityCode("XYZ");
		return "Account Code: " + existingOrder.getSecurityCode();
	}

	@Override
	public Order createOrder(Order order) {
		Order newOrder = new Order();
		newOrder.setOrderId(new Random().nextInt());
		newOrder.setSecurityCode("XYZ");
		newOrder.setOrderStatus("INITIATED");
		newOrder.setOrderDate(new Date());
		return newOrder;
	}

	@Override
	public Order getOrder(int id) {
		Order newOrder = new Order();
		newOrder.setOrderId(new Random().nextInt());
		newOrder.setSecurityCode("XYZ");
		newOrder.setOrderStatus("COMPLETED");
		newOrder.setOrderDate(new Date());
		newOrder.setDescription("This is the new XYZ order");
		return newOrder;
	}

	private void privateTest(String name) {
		LOGGER.debug(name);
	}
}
