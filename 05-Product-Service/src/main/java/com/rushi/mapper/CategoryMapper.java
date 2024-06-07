package com.rushi.mapper;

import org.modelmapper.ModelMapper;

import com.rushi.dto.CategoryDTO;
import com.rushi.entity.Category;

public class CategoryMapper {
	
	private static ModelMapper mapper=new ModelMapper();
	
	public static CategoryDTO convertToDto(Category category) {
		
		return mapper.map(category,CategoryDTO.class);
	}
	
	public static Category convertToEntity(CategoryDTO categoryDto) {
		return mapper.map(categoryDto, Category.class);
	}

}
