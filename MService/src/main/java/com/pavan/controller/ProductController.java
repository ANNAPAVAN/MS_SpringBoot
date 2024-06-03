package com.pavan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.dto.ProductRequest;
import com.pavan.dto.ProductResponse;
import com.pavan.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	Environment environment;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@Valid @RequestBody ProductRequest productRequest) {
		productService.createProduct(productRequest);
	}
	
	@GetMapping 
	@ResponseStatus(HttpStatus.OK) 
	public List<ProductResponse> getAllProducts(){
		System.out.println(environment.getProperty("local.server.port"));
		return productService.getAllProducts();
	}
	

}
