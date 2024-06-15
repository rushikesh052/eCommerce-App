package com.rushi.repo;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rushi.entity.ProductOrder;

public interface OrderRepo extends JpaRepository<ProductOrder, Integer>{

	public List<ProductOrder> findByUserId(Integer userId);

	public List<ProductOrder> findByOrderDateAndOrderStatus(LocalDate orderDate, String orderStatus);

	
	


	
}
