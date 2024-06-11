package com.rushi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rushi.dto.ProductDTO;

public interface IProductService {
	
	public ProductDTO addProduct(ProductDTO productDto,MultipartFile file)throws Exception;
	
	public ProductDTO updateProduct(Integer produdctId,MultipartFile file, ProductDTO productDto)throws Exception;
	
	public List<ProductDTO> getAllProduct();
	
	public ProductDTO getProductById(Integer productId);
	
	public ProductDTO deleteProductById(Integer ProductId);

	public ProductDTO updateStock(Integer productId, Integer quantity);
	
	
}
