package com.local.inventory.controller;

import java.io.IOException;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.local.inventory.model.Product;
import com.local.inventory.response.ProductResponseRest;
import com.local.inventory.services.IProductService;
import com.local.inventory.util.Util;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
	private IProductService productService;

	public ProductRestController(IProductService productService) {
		super();
		this.productService = productService;
	}
	/**
	 * 
	 * @param picture
	 * @param name
	 * @param price
	 * @param account
	 * @param categoryID
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/products")
	public ResponseEntity<ProductResponseRest> save(
			@RequestParam("picture") MultipartFile picture,
			@RequestParam("name") String name, 
			@RequestParam("price") int price, 
			@RequestParam("account") int account,
			@RequestParam("categoryId") Long categoryID) throws IOException {

		Product product = new Product();
		product.setName(name);
		product.setAccount(account);
		product.setPrice(price);
		product.setPicture(Util.compressZLib(picture.getBytes()));

		ResponseEntity<ProductResponseRest> response = productService.save(product, categoryID);
		return response;
	}
	
	
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> searchById(@PathVariable long id){
		ResponseEntity<ProductResponseRest> response= productService.searchById(id);
		return response;
	}
	
	/***
	 * search by name
	 * @param name
	 * @return
	 */
	@GetMapping("/products/filter/{name}")
	public ResponseEntity<ProductResponseRest> searchById(@PathVariable String name){
		ResponseEntity<ProductResponseRest> response= productService.searchByName(name);
		return response;
	}
	/**
	 * delete by id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/products/delete/{id}")
	public ResponseEntity<ProductResponseRest> searchById(@PathVariable Long id){
		ResponseEntity<ProductResponseRest> response= productService.deleteById(id);
		return response;
	}
	
	@GetMapping("/products")
	public ResponseEntity<ProductResponseRest> search(){
		ResponseEntity<ProductResponseRest> response= productService.search();
		return response;
	}

}









