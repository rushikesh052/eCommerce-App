package com.rushi.rest;

import java.util.List;
import java.util.Map;

import org.apache.hc.core5.http.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rushi.constants.AppConstants;
import com.rushi.dto.CategoryDTO;
import com.rushi.properties.AppProperties;
import com.rushi.response.ApiResponse;
import com.rushi.service.CategoryServiceImpl;

@RestController
public class CategoryRestController {
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@Autowired
	private AppProperties appProp;
	
	
	@PostMapping("/addcategory")
	public ResponseEntity<ApiResponse<CategoryDTO>> createCategory(@RequestBody CategoryDTO category){
		
		ApiResponse<CategoryDTO> response=new ApiResponse<>();
		Map<String, String> messages=appProp.getMessages();
		CategoryDTO addedCategory=categoryService.addCategory(category);
		if(addedCategory!=null) {
			response.setStatus(201);
			response.setMessage(messages.get(AppConstants.ADD_CATEGORY));
			response.setData(addedCategory);
			return new ResponseEntity<> (response,HttpStatus.CREATED);
		}else {
			response.setStatus(501);
			response.setMessage(messages.get(AppConstants.ADD_CATEGORY_ERR));
			return new ResponseEntity<> (response,HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<ApiResponse<CategoryDTO>> updateCategory(@PathVariable Integer categoryId,@RequestBody CategoryDTO categoryDto){
		ApiResponse<CategoryDTO> response=new ApiResponse<>();
		Map<String,String> messages=appProp.getMessages();
		
		CategoryDTO updateCategory=categoryService.update(categoryId, categoryDto);
		if(updateCategory!=null) {
			response.setStatus(201);
			response.setMessage(messages.get(AppConstants.CATEGORY_UPDATE));
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			response.setStatus(501);
			response.setMessage(messages.get(AppConstants.CATEGORY_UP_ERR));
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
	}
	
    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllCategories() {
    	
    	 List<CategoryDTO> allCategories = categoryService.getAllCategory();
         ApiResponse<List<CategoryDTO>> response = new ApiResponse<>();
         Map<String, String> messages = appProp.getMessages();
             response.setStatus(200);
             response.setMessage(messages.get(AppConstants.ALL_CATEGORY));
             response.setData(allCategories);
         return new ResponseEntity<>(response, HttpStatus.OK);
     }
    
    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> getCategoryById(@PathVariable("id")Integer categoryId){
    	CategoryDTO categoryDto=categoryService.getCategoryById(categoryId);
    	ApiResponse<CategoryDTO> response=new ApiResponse<>();
    	Map<String, String> messages=appProp.getMessages();
    	if(categoryDto!=null) {
    	response.setStatus(200);
    	response.setMessage(messages.get(AppConstants.CATEGORY_RETRIEVE));
    	response.setData(categoryDto);
    	return new ResponseEntity<>(response,HttpStatus.OK);
    	}else {
    	response.setStatus(500);
    	response.setMessage(messages.get(AppConstants.CATEGORY_NOT_FOUND));
    	return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    @DeleteMapping("/deletecategory/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> deleteCategoryById(@PathVariable("id")Integer categoryId){
    	CategoryDTO categoryDto=categoryService.deleteCategoryById(categoryId);
    	
    	ApiResponse<CategoryDTO> response =new ApiResponse<>();
    	Map<String, String> messages=appProp.getMessages();
    	if(categoryDto!=null) {
    	response.setStatus(200);
    	response.setMessage(messages.get(AppConstants.CATEGORY_DELETE));
    	response.setData(categoryDto);
    	return new ResponseEntity<>(response,HttpStatus.OK);
    	}else {
    		response.setStatus(500);
    		response.setMessage(messages.get(AppConstants.CATEGORY_NOT_FOUND_FOR_DELETE));
    		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }	
}
