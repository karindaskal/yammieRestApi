package com.yammie.yammie.controller;

import com.yammie.yammie.model.Order;
import com.yammie.yammie.model.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.*;

/**
 * controller class implements
 */
@RestController

public class OrderControllerImp implements OrderController {
	@Autowired
	OrderRepository orderRepository;


	/**
	 * save order
	 * post request
	 * @param order
	 * @return
	 */
	@PostMapping("/order")
	public ResponseEntity<Order> SaveOrder(@RequestBody Order order) {
		try {

			Order _tutorial = orderRepository
					.save(new Order(order.getAdress(),order.getDescription(),order.getPrice(),order.getName(),new Date(System.currentTimeMillis())));
			return new ResponseEntity<>(order, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Get all orders from the last day
	 *  get request
	 * @return
	 */
	@GetMapping("/order")
	@Override
	public  ResponseEntity<List<Order>>  getOrderLastDay() {
		try {
			// current time
			Date dateE=new Date(System.currentTimeMillis());
              //Calculates the time before 24 hours
			Date dateS=new Date(System.currentTimeMillis() - 3600 * 24000);

			List<Order> order = orderRepository.findAllByDateBetween(dateS,dateE);

			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
