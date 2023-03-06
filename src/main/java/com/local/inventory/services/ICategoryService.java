package com.local.inventory.services;

import org.springframework.http.ResponseEntity;

import com.local.inventory.model.Category;
import com.local.inventory.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
	public ResponseEntity<CategoryResponseRest> searchById(Long id);
	public ResponseEntity<CategoryResponseRest> save(Category category);
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id);
	
	

}
