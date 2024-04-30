package com.blueyonder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blueyonder.model.Product;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>  {

	@Modifying
    @Transactional
	@Query(value="update product set price=price+price*20/100, date_time = CURRENT_TIMESTAMP",nativeQuery=true)
	void modifyProductData();

	@Modifying
    @Transactional
	@Query(value="update product set sold_qty=sold_qty+:p_qty where p_id=:p_id",nativeQuery=true)
	void modifyProductQuantity(Long p_id, Long p_qty);

}
