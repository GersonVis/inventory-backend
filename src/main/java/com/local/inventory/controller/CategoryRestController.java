package com.local.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.local.inventory.model.Category;
import com.local.inventory.response.CategoryResponseRest;
import com.local.inventory.services.ICategoryService;
import com.local.inventory.response.CategoryResponseRest;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
	
	@Autowired
	private ICategoryService service; 
	
	
	/**
	 * get all categories
	 * @return
	 */
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> searchCategories() {
			ResponseEntity<CategoryResponseRest> response  = service.search();
			return response;
	}
	

	/**
	 * get categories by id
	 * @param id
	 * @return
	 */
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable long id){
		   ResponseEntity<CategoryResponseRest> response = service.searchById(id);
		   return response;
	}
	/**
	 * save categories
	 * @param category
	 * @return
	 */
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category){
		   ResponseEntity<CategoryResponseRest> response = service.save(category);
		   return response;
	}
}
