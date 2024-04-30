package com.blueyonder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blueyonder.model.Order;
import com.blueyonder.model.Product;
import com.blueyonder.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/get")
	public ResponseEntity<List<Product>> getProduct(){
		return new ResponseEntity<>(productService.getProduct(),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		return new ResponseEntity<>(productService.addProduct(product),HttpStatus.OK);
	}
	
	@PutMapping("/updateQuantity")
	public ResponseEntity updateProductQuantity(@RequestBody Order order) {
		return productService.updateProductData(order);
	}

}
