package com.local.inventory.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.local.inventory.dao.ICategoryDao;
import com.local.inventory.model.Category;
import com.local.inventory.response.CategoryResponseRest;


@Service
public class CategoryServiceImpl implements ICategoryService{
	
	
	@Autowired
	private ICategoryDao categoryDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> search() {
		CategoryResponseRest response = new CategoryResponseRest();
		
		try {
			List<Category> category = (List<Category>) categoryDao.findAll();
			
			response.getCategoryResponse().setCategory(category);
			response.setMetada("Repuesta ok", "00", "Respuesta exitosa");
		}catch(Exception e) {
			response.setMetada("Repuesta 404", "-1", "Respuesta incorrecta");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchById(Long Id) {
		CategoryResponseRest response= new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		try {
			Optional<Category> category = categoryDao.findById(Id);
			if(category.isPresent()) {
				list.add(category.get());
				response.getCategoryResponse().setCategory(list);
			}else {
				response.setMetada("Respuesta 404", "-1", "No hay registros con el id proporcionado");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.FORBIDDEN);
			}
			
		}catch(Exception e) {
			response.setMetada("Repuesta 404", "-1", "Error en la solicitud");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}


























