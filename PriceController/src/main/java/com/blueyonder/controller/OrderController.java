package com.blueyonder.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueyonder.model.Order;
import com.blueyonder.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/get")
	public ResponseEntity<List<Order>> getOrderDetails(){
		return new ResponseEntity<>(orderService.getOrder(),HttpStatus.OK);
	}
	@PostMapping("/add")
	public ResponseEntity<Order> addOrderDetails(@RequestBody Order order){
		return new ResponseEntity<>(orderService.addOrder(order),HttpStatus.OK);
	}
	
	@GetMapping("/get/purchasedOrNot")
	public ResponseEntity<Map<Long,Long>> getPurchasedOrder() {
		return new ResponseEntity<>(orderService.getPIDs());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteOrderDetail(@PathVariable Long id) {
		return orderService.deleteOrder(id);
	}

}
