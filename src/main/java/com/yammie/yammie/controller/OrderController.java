package com.yammie.yammie.controller;

import com.yammie.yammie.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Controller interface
 */

public interface OrderController {
	/**
	 * Save Order
	 * @param order
	 * @return
	 */
	public ResponseEntity<Order> SaveOrder(@RequestBody Order order);

	/**
	 * Get all orders from the last day
	 * @return
	 */
	public  ResponseEntity<List<Order>>  getOrderLastDay();

}
