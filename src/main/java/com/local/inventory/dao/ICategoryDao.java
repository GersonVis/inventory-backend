package com.local.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.local.inventory.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long>{

}
