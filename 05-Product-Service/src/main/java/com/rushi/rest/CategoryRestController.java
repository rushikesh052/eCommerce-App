package com.rushi.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryDTO category){
		
		ApiResponse<CategoryDTO> response=new ApiResponse<>();
		Map<String, String> msg=appProp.getMessages();
		CategoryDTO addedCategory=categoryService.addCategory(category);
		if(addedCategory!=null) {
			response.setStatus(201);
			response.setMessage(msg.get(AppConstants.ADD_CATEGORY));
			response.setData(addedCategory);
		}else {
			response.setStatus(401);
			response.setMessage(msg.get(AppConstants.ADD_CATEGORY_ERR));
		}
		return new ResponseEntity<> (response,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable Integer categoryId,@RequestBody CategoryDTO categoryDto){
		ApiResponse<CategoryDTO> response=new ApiResponse<>();
		Map<String,String> msg=appProp.getMessages();
		
		CategoryDTO updateCategory=categoryService.update(categoryId, categoryDto);
		if(updateCategory!=null) {
			response.setStatus(201);
			response.setMessage(msg.get(AppConstants.CATEGORY_UPDATE));
		}else {
			response.setStatus(501);
			response.setMessage(msg.get(AppConstants.CATEGORY_UP_ERR));
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> allCategories = categoryService.getAllCategory();
        return new ResponseEntity<> (allCategories,HttpStatus.OK);
    }
	
	
}
