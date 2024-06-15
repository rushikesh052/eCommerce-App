package com.rushi.dto;

import java.time.LocalDate;

import com.rushi.entity.OrderStatus;

public class ReportsDto {
	
	private OrderStatus status;
	
	private LocalDate startDate;
	
	private LocalDate endDate;

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	

}
