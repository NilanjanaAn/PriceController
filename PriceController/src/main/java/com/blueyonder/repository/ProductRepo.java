package com.blueyonder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blueyonder.model.Product;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>  {

	@Query(value="select * from product order by p_id",nativeQuery=true)
	List<Product> getAllSorted();

	@Modifying
    @Transactional
//	@Query(value="update product set price=price+price*20/100, date_time = CURRENT_TIMESTAMP",nativeQuery=true)
	@Query(value="update product set\n" +
			"price = CASE\n" +
			"WHEN sold_qty<:threshold THEN price-price*5/100\n" +
			"ELSE price\n" +
			"END,\n" +
			"last_updated_date_time = CASE\n" +
			"WHEN sold_qty<:threshold THEN CURRENT_TIMESTAMP\n" +
			"ELSE last_updated_date_time\n" +
			"END,\n" +
			"last_checked_date_time = CURRENT_TIMESTAMP,\n" +
			"sold_qty = 0",nativeQuery=true)
	void modifyProductData(Long threshold);

	@Modifying
    @Transactional
	@Query(value="update product set sold_qty=sold_qty+:p_qty where p_id=:p_id",nativeQuery=true)
	void modifyProductQuantity(Long p_id, Long p_qty);

}
