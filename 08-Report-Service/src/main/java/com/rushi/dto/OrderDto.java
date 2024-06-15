package com.rushi.dto;

import java.time.LocalDate;

import com.rushi.entity.OrderStatus;

import lombok.Data;



@Data
public class OrderDto {

	private Integer orderId;
	private Integer userId;
	private Integer productId;
	private Integer quantity;
	private Integer price;
	private String paymentType;
	private OrderStatus status;
	private LocalDate orderDate;
}
