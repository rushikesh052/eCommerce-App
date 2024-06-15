package com.rushi.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.rushi.dto.ProductOrderDto;
import com.rushi.entity.ProductOrder;



@Component
public class OrderMapper {
	
	private static final ModelMapper mapper = new ModelMapper();

	public static ProductOrderDto convertToDto(ProductOrder order) {
		return mapper.map(order, ProductOrderDto.class);
	}

	public static ProductOrder convertToEntity(ProductOrderDto orderDto) {
		return mapper.map(orderDto, ProductOrder.class);
	}

}
