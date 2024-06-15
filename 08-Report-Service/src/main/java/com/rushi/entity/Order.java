package com.rushi.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private Integer userId;
	private Integer productId;
	private Integer quantity;
	private Integer price;
	private String paymentType;
	
	@Enumerated(EnumType.STRING)
	@Column(name="orderstatus")
	private OrderStatus status;
	
	@Column
	private LocalDate orderDate;
	
	
	
}
