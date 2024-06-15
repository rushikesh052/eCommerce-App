package com.rushi.service;

import java.time.LocalDate;
import java.util.List;

import com.rushi.dto.ProductOrderDto;

public interface IOrderService {
	public List<ProductOrderDto> addOrder(List<ProductOrderDto> productOrderDto);
	public ProductOrderDto updateOrder(ProductOrderDto productOrderDto);
	public List<ProductOrderDto> getOrderByUserId(Integer userId);
	public List<ProductOrderDto> getOrderByDateAndStauts(LocalDate orderDate,String orderStatus);
	public List<ProductOrderDto> getAllOrders();
}
