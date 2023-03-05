package com.local.inventory.services;

import org.springframework.http.ResponseEntity;

import com.local.inventory.response.CategoryResponseRest;

public interface ICategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();

}
