package com.rushi.mapper;

import org.modelmapper.ModelMapper;

import com.rushi.dto.OrderDto;
import com.rushi.entity.Order;

public class OrderMapper {
	
	public static final ModelMapper mapper=new ModelMapper();
	
	
	public static OrderDto ConvertToDto(Order order) {
	return mapper.map(order, OrderDto.class);
	}
	
	public static Order ConvertToEntity(OrderDto orderDto) {
		return mapper.map(orderDto, Order.class);
		}

}
