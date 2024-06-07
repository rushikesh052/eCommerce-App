package com.rushi.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rushi.constants.AppConstants;
import com.rushi.dto.ProductDTO;
import com.rushi.properties.AppProperties;
import com.rushi.response.ApiResponse;
import com.rushi.service.ProductService;

@RestController
public class ProductRestController {

	@Autowired
	private ProductService prodService;
	
	@Autowired
	private AppProperties appProp;
	
	@PostMapping("/addproduct")
	public ResponseEntity<ApiResponse<ProductDTO>> addProduct(@RequestParam("data") String productJson,@RequestParam("file") MultipartFile file)throws Exception{
		
		ApiResponse<ProductDTO> response=new ApiResponse<>();
		Map<String,String> message=appProp.getMessages();
		ObjectMapper mapper=new ObjectMapper();
		ProductDTO product = mapper.readValue(productJson, ProductDTO.class);
		ProductDTO addedProduct=prodService.addProduct(product, file);
		
		if(addedProduct==null) {
			response.setStatus(201);
			response.setMessage(message.get(AppConstants.ADD_PROD));
			response.setData(addedProduct);
		}else {
			response.setStatus(501);
			response.setMessage(message.get(AppConstants.ADD_PROD_ERROR));
		}
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	
}
