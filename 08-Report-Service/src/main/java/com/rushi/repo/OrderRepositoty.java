package com.rushi.repo;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rushi.entity.Order;
import com.rushi.entity.OrderStatus;

public interface OrderRepositoty extends JpaRepository<Order, Integer>{
	
	
	public List<Order> findByStatus(OrderStatus status);
	

    @Query("Select o from Order o where o.orderDate between :startDate and :endDate")
    List<Order> findOrdersBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);}
