package com.blueyonder.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blueyonder.model.Order;
import com.blueyonder.model.Product;
import com.blueyonder.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo repo;
	
	public List<Product> getProduct(){
		return repo.findAll();
	}

	public Product addProduct(Product product) {
		product.setDate_time(LocalDateTime.now());
		return repo.save(product);
	}

	public void updateProductData() {
		repo.modifyProductData();
		
	}

	public void modify(Long p_id, Long p_qty) {
		repo.modifyProductQuantity(p_id,p_qty);
		
	}

	public ResponseEntity updateProductData(Order order) {
		for(int i=0;i<order.getP_id().size();i++) {
			modify(order.getP_id().get(i),order.getP_qty().get(i));
		}
		return new ResponseEntity(HttpStatus.OK);
	}
}
