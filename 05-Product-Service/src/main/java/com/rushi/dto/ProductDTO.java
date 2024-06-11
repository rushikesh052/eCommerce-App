package com.rushi.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {
	
	private Integer productId;
	
	private String name;
	
	private String description; 
	
	private Double price; 
	
	private Integer stock;

	private String productPic;
	
	private Integer discount;
	
	private Double pricebeforeDiscount;
	
	private Integer categoryId;

}
