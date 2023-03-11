package com.local.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.local.inventory.model.Product;

public interface IProductDao extends CrudRepository<Product, Long>{

}
