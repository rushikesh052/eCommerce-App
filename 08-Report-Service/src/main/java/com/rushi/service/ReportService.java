package com.rushi.service;

import java.time.LocalDate;
import java.util.List;

import com.rushi.dto.OrderDto;
import com.rushi.entity.OrderStatus;

public interface ReportService {

	public  List<OrderDto> getAllOrders();
	
	public List<OrderDto> orderByStatus(OrderStatus status);
	
	public List<OrderDto> getOrdersBetweenDate(LocalDate startDate, LocalDate endDate);
	
}
