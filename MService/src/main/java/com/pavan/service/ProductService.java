package com.pavan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pavan.dto.ProductRequest;
import com.pavan.dto.ProductResponse;
import com.pavan.models.Product;
import com.pavan.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	

	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		
		productRepository.save(product);
		log.info("product {} is saved", product.getId());  // using Slf4j
	}
	
	public List<ProductResponse> getAllProducts() {
		List<Product> products =  productRepository.findAll();
		
		return products.stream().map(this::mapToProductResponse).toList();
	}
	
	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}

}
