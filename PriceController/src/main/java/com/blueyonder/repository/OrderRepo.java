package com.blueyonder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blueyonder.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

	@Query(value="select * from orders where date_time>= NOW() - INTERVAL ':i minutes'",nativeQuery=true)
	List<Order> findAllDataByTime(int i);

}
