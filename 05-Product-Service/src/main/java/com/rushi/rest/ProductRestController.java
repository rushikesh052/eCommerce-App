package com.rushi.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity <ApiResponse<ProductDTO>> addProduct(@RequestParam("data") String productJson,@RequestParam("file") MultipartFile file)throws Exception{
		
		ApiResponse<ProductDTO> response=new ApiResponse<>();
		Map<String,String> message=appProp.getMessages();
		ObjectMapper mapper=new ObjectMapper();
		ProductDTO product = mapper.readValue(productJson, ProductDTO.class);
		ProductDTO addedProduct=prodService.addProduct(product, file);
		
		if(addedProduct!=null) {
			response.setStatus(201);
			response.setMessage(message.get(AppConstants.ADD_PROD));
			response.setData(addedProduct);
		}else {
			response.setStatus(501);
			response.setMessage(message.get(AppConstants.ADD_PROD_ERROR));
		}
		return new ResponseEntity<>(response,HttpStatus.CREATED); 
		
	}
	
	@PutMapping("/product/{productId}")
	public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(
			@PathVariable("productId") Integer productId, @RequestParam("data") String productJson,
			@RequestParam("file") MultipartFile file )throws Exception{
		ApiResponse<ProductDTO> response=new ApiResponse<>();
	
		Map<String,String> message=appProp.getMessages();
		ObjectMapper mapper=new ObjectMapper();
		ProductDTO productDto=mapper.readValue(productJson, ProductDTO.class);
		ProductDTO updateProduct=prodService.updateProduct(productId, file, productDto);
		
		if(updateProduct != null) {
			response.setStatus(200);
			response.setMessage(message.get(AppConstants.UPDATE_PROD));
			response.setData(updateProduct);
		}
		else {
			response.setStatus(500);
			response.setMessage(message.get(AppConstants.UPDATE_PROD_ERR));
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {
        List<ProductDTO> allProducts = prodService.getAllProduct();
        ApiResponse<List<ProductDTO>> response = new ApiResponse<>();
        Map<String, String> message = appProp.getMessages();
        response.setStatus(200);
        response.setMessage(message.get(AppConstants.ALL_PRODUCT_RETRIEVE));
        response.setData(allProducts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	
	@GetMapping("/product/{productId}")
	public ResponseEntity<ApiResponse<ProductDTO>> getProductById(@PathVariable("productId") Integer productId) {
		ProductDTO productDto=prodService.getProductById(productId);
		
		ApiResponse<ProductDTO> response=new ApiResponse<>();
		Map<String,String> messages=appProp.getMessages();
		if(productDto!=null) {
		response.setStatus(200);
		response.setMessage(messages.get(AppConstants.PRODUCT_RETRIEVE));
		response.setData(productDto);
		
		}
		else {
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.PRODUCTNOTFOUND));
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteproduct/{productId}")
	public ResponseEntity<ApiResponse<ProductDTO>> productDeleteById(@PathVariable("productId") Integer productId){
		ProductDTO deletedProduct=prodService.deleteProductById(productId);
		
		ApiResponse<ProductDTO> response=new ApiResponse<>();
		Map<String,String> messages=appProp.getMessages();
		if(deletedProduct !=null) {
		response.setStatus(200);
		response.setMessage(messages.get(AppConstants.DELETE_PRODUCT));
		response.setData(deletedProduct);
		
		}
		else {
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.PRODUCT_NOT_FOUND_FOR_DELETE));
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping("product/{productId}/stock")
	public ResponseEntity<ApiResponse<ProductDTO>> updateStock(@PathVariable("productId") Integer productId,@RequestParam("stock") Integer quantity){
		ProductDTO updateStock = prodService.updateStock(productId, quantity);
		ApiResponse<ProductDTO> response=new ApiResponse<>();
		Map<String,String> messages=appProp.getMessages();
		if(updateStock!=null) {
			response.setStatus(200);
			response.setMessage(messages.get(AppConstants.UPDATE_STOCK));
			response.setData(updateStock);
			
		}
		else {
		response.setStatus(500);
		response.setMessage(messages.get(AppConstants.PRODUCT_NOT_FOUND));
		}
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
}
