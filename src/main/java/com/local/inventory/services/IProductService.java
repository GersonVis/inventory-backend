package com.local.inventory.services;

import org.springframework.http.ResponseEntity;

import com.local.inventory.model.Product;
import com.local.inventory.response.ProductResponseRest;

public interface IProductService {
	public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);
	public ResponseEntity<ProductResponseRest> searchById(Long id);
	public ResponseEntity<ProductResponseRest> searchByName(String name);
}
