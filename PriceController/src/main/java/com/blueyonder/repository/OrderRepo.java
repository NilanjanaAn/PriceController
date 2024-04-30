package com.blueyonder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blueyonder.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

}
