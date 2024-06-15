package com.rushi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rushi.dto.ProductOrderDto;
import com.rushi.entity.ProductOrder;
import com.rushi.mapper.OrderMapper;
import com.rushi.repo.OrderRepo;

@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private OrderRepo orderRepo;

	@Override
	public List<ProductOrderDto> addOrder(List<ProductOrderDto> productOrderDto) {
		 List<ProductOrder> orders = productOrderDto.stream()
		            .map(OrderMapper::convertToEntity)
		            .collect(Collectors.toList());
		        List<ProductOrder> savedOrders = orderRepo.saveAll(orders);
		        return savedOrders.stream()
		            .map(OrderMapper::convertToDto)
		            .collect(Collectors.toList());
	}

	@Override
	public ProductOrderDto updateOrder(ProductOrderDto productOrderDto) {
		ProductOrder order = OrderMapper.convertToEntity(productOrderDto);
        ProductOrder updatedOrder = orderRepo.save(order);
        return OrderMapper.convertToDto(updatedOrder);
	}

	@Override
	public List<ProductOrderDto> getOrderByUserId(Integer userId) {
		List<ProductOrder> orders = orderRepo.findByUserId(userId);
        return orders.stream()
            .map(OrderMapper::convertToDto)
            .collect(Collectors.toList());
	}

	@Override
	public List<ProductOrderDto> getOrderByDateAndStauts(LocalDate orderDate, String orderStatus) {
		List<ProductOrder> orders = orderRepo.findByOrderDateAndOrderStatus(orderDate, orderStatus);
        return orders.stream()
            .map(OrderMapper::convertToDto)
            .collect(Collectors.toList());
	}

	@Override
	public List<ProductOrderDto> getAllOrders() {
		List<ProductOrder> orders = orderRepo.findAll();
        return orders.stream()
            .map(OrderMapper::convertToDto)
            .collect(Collectors.toList());
	}

	
}
