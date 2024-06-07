package com.rushi.service;

import java.util.List;

import com.rushi.dto.CategoryDTO;

public interface ICategoryService {
	
	public CategoryDTO addCategory(CategoryDTO categoryDto);
	
	public CategoryDTO update(Integer categoryId,CategoryDTO categoryDto);
	
	public  List<CategoryDTO> getAllCategory();
	
	public CategoryDTO getCategoryById(Integer categoryId);
	
	public CategoryDTO deleteCategoryById(Integer categoryId);
	
	

}
