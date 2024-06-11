package com.rushi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.rushi.dto.ProductDTO;
import com.rushi.entity.Category;
import com.rushi.entity.Product;
import com.rushi.mapper.ProductMapper;
import com.rushi.repo.CategoryRepo;
import com.rushi.repo.ProductRepo;
import com.rushi.util.FileUtils;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public ProductDTO addProduct(ProductDTO productDto, MultipartFile file)throws Exception {
		Product product=ProductMapper.convertToEntity(productDto);
		String fileName = FileUtils.saveFile(file.getName(), file);
		product.setProductPic(fileName);
		Integer categoryId=productDto.getCategoryId();
		Category category=categoryRepo.findById(categoryId).orElseThrow();
		product.setCategory(category);
		Product savedProduct=productRepo.save(product);
		return ProductMapper.convertToDto(savedProduct);
	}

	@Override
	public ProductDTO updateProduct(Integer productId, MultipartFile file, ProductDTO productDto)throws Exception {
		Product existProduct=productRepo.findById(productId).orElseThrow();
		existProduct.setName(productDto.getName());
		existProduct.setDescription(productDto.getDescription());
		existProduct.setPrice(productDto.getPrice());
		existProduct.setStock(productDto.getStock());
		existProduct.setDiscount(productDto.getDiscount());
		existProduct.setPricebeforeDiscount(productDto.getPricebeforeDiscount());
		String fileName=FileUtils.saveFile(file.getName(), file);
		existProduct.setProductPic(fileName);
		Product updatedProduct = productRepo.save(existProduct);
		return ProductMapper.convertToDto(updatedProduct);
	}

	@Override
	public List<ProductDTO> getAllProduct() {
		List<Product> products=productRepo.findAll();
		
		List<ProductDTO> productDtos=products.stream().
				map(ProductMapper::convertToDto)
				.collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public ProductDTO getProductById(Integer productId) {
		Product product=productRepo.findById(productId).orElseThrow();
		ProductDTO productDto=ProductMapper.convertToDto(product);
		return productDto;
	}

	@Override
	public ProductDTO deleteProductById(Integer ProductId) {
		Product product=productRepo.findById(ProductId).orElseThrow();
		ProductDTO productDto=ProductMapper.convertToDto(product);
		productRepo.deleteById(ProductId);
		return productDto;
	}

	@Override
	public ProductDTO updateStock(Integer productId, Integer quantity) {
		Product product=productRepo.findById(productId).orElseThrow();
		ProductDTO productDto=ProductMapper.convertToDto(product);
		product.setStock(quantity);
		productRepo.save(product);
		return productDto;
	}
}
