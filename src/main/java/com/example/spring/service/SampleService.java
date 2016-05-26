package com.example.spring.service;

import com.example.model.Order;

/**
 * @author gimbyeongsu
 *
 */
public interface SampleService {
	public String getOrderDescription();
	public String getOrderStringCode();
	public Order getOrder(int id);
	public Order createOrder(Order order);
}
