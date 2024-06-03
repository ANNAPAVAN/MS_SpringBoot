package com.pavan.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pavan.models.Product;


public interface ProductRepository extends MongoRepository<Product, String> {

}
