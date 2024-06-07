package com.rushi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rushi.dto.CategoryDTO;
import com.rushi.entity.Category;
import com.rushi.mapper.CategoryMapper;
import com.rushi.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public CategoryDTO addCategory(CategoryDTO categoryDto) {
		Category category=CategoryMapper.convertToEntity(categoryDto);
		
		Category saveCategory=categoryRepo.save(category);
		return CategoryMapper.convertToDto(saveCategory);
	}

	@Override
	public CategoryDTO update(Integer categoryId, CategoryDTO categoryDto) {
		Category category=categoryRepo.findById(categoryId).orElseThrow();
		category.setCategoryName(category.getCategoryName());
		Category updatedCategory =categoryRepo.save(category);
		return CategoryMapper.convertToDto(updatedCategory);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		 List<Category> allCategories = categoryRepo.findAll();
		 
		 List<CategoryDTO> categoryDTOs = allCategories.stream().
				 					map(CategoryMapper::convertToDto)
				 					.collect(Collectors.toList());
		 return categoryDTOs;
	}

	@Override
	public CategoryDTO getCategoryById(Integer categoryId) {
		Category category=categoryRepo.findById(categoryId).orElseThrow();
		CategoryDTO categoryDto = CategoryMapper.convertToDto(category);
		return categoryDto;
	}

	@Override
	public CategoryDTO deleteCategoryById(Integer categoryId) {
		Category category=categoryRepo.findById(categoryId).orElseThrow();
		CategoryDTO categoryDto=CategoryMapper.convertToDto(category);
		categoryRepo.deleteById(categoryId);
		return categoryDto;
	}

}
