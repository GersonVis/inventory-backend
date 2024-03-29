package com.local.inventory.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
				response.setMetada("Repuesta ok", "00", "Búsqueda exitosa");
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
	

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> save(Category category) {
		CategoryResponseRest response= new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		try {
			Category categorySaved = categoryDao.save(category);
			if(categorySaved != null) {
				list.add(categorySaved);
				response.getCategoryResponse().setCategory(list);
				response.setMetada("Respuesta ok", "00", "Se ha guardado correctamente");
			}else {
				response.setMetada("Respuesta nok", "-1", "Categoria no guardada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			response.setMetada("Repuesta 404", "-1", "Error al guardar la información");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
		CategoryResponseRest response= new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		try {
			Optional<Category> categorySearch = categoryDao.findById(id);
			if(categorySearch.isPresent()) {
				categorySearch.get().setName(category.getName());
				categorySearch.get().setDescription(category.getDescription());
				
				Category categoryToUpdate = categoryDao.save(categorySearch.get());
				if(categoryToUpdate != null) {
					list.add(categoryToUpdate);
					response.getCategoryResponse().setCategory(list);
					response.setMetada("Respuesta ok", "1", String.format("Se actualizo correctamente ID: %s", id));
				}else {
					response.setMetada("Respuesta nok", "-1", String.format("No se pudo actualizar la categoria con ID: %s", id));
					return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NO_CONTENT);
				}
 			}else {
				response.setMetada("Respuesta nok", "-1", "ID de registro no encontrado");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
			}
		}catch(Exception e) {
			response.setMetada("Repuesta 404", "-1", "Error al actualizar la información");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
		CategoryResponseRest response= new CategoryResponseRest();
		
		try {
			categoryDao.deleteById(id);
			response.setMetada("Respuesta ok", "00", "Se ha eliminado el registro satisfactoriamente");
		}catch(Exception e) {
			response.setMetada("Repuesta 404", "-1", "Error al eliminar categoria");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}
	
	
	

}


























