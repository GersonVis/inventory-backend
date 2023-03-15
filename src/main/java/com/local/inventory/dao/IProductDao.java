package com.local.inventory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.local.inventory.model.Product;

public interface IProductDao extends CrudRepository<Product, Long>{
	    @Query("select p from Product p where p.name like %?1%")
		List<Product> findByNameLike(String name);
	 

		List<Product> findByNameContainingIgnoreCase(String name);
}
//Reason: Validation failed for query for method public abstract java.util.List com.local.inventory.dao.IProductDao.findByNameLike(java.lang.String)