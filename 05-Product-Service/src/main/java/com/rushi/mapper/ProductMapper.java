package com.rushi.mapper;

import org.modelmapper.ModelMapper;


import com.rushi.dto.ProductDTO;
import com.rushi.entity.Product;

public class ProductMapper {

private static ModelMapper mapper=new ModelMapper();
	
	public static ProductDTO convertToDto(Product Product) {
		
		return mapper.map(Product,ProductDTO.class);
	}
	
	public static Product convertToEntity(ProductDTO productDto) {
		return mapper.map(productDto, Product.class);
	}
}
