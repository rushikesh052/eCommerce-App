package com.rushi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rushi.dto.OrderDto;
import com.rushi.entity.Order;
import com.rushi.entity.OrderStatus;
import com.rushi.mapper.OrderMapper;
import com.rushi.repo.OrderRepositoty;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private OrderRepositoty orderRepo;
	
	@Override
	public List<OrderDto> getAllOrders() {

		List<Order> orders = orderRepo.findAll();

		return orders.stream()
					  .map(OrderMapper::ConvertToDto)
					  .collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> orderByStatus(OrderStatus status) {
		List<Order> byStatus = orderRepo.findByStatus(status);
		return byStatus.stream()
					   .map(OrderMapper::ConvertToDto)
					   .collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> getOrdersBetweenDate(LocalDate startDate, LocalDate endDate) {
		List<Order> dates = orderRepo.findOrdersBetweenDates(startDate, endDate);
		return dates.stream()
				    .map(OrderMapper::ConvertToDto)
				    .collect(Collectors.toList());
	}

}
