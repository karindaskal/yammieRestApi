package com.yammie.yammie.controller;

import com.yammie.yammie.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrderController {
	public ResponseEntity<Order> SaveOrder(@RequestBody Order order);
	public  ResponseEntity<List<Order>>  getOrderLastDay();

}
