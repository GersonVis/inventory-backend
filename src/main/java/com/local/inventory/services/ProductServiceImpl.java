package com.local.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.local.inventory.dao.ICategoryDao;
import com.local.inventory.dao.IProductDao;
import com.local.inventory.model.Product;
import com.local.inventory.response.ProductResponseRest;
import com.local.inventory.util.Util;

import org.springframework.transaction.annotation.Transactional;

import com.local.inventory.model.Category;



@Service
public class ProductServiceImpl implements IProductService {
	private ICategoryDao categoryDao;
	private IProductDao productDao;

	public ProductServiceImpl(ICategoryDao categoryDao, IProductDao productDao) {
		super();
		this.categoryDao = categoryDao;
		this.productDao = productDao;
	}
	
	
	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		try {
			Optional<Category> category = categoryDao.findById(categoryId);
			if(category.isPresent()) {
				product.setCategory(category.get());
			}else {
				response.setMetada("Respuesta ok", "-1", "Categoría no encontrada");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			Product productSave = productDao.save(product);
			if(productSave!=null) {
				list.add(productSave);
				response.setMetada("Respuesta ok", "00", "Producto registrado");
				response.getProduct().setProducts(list);
			}
			
		}catch(Exception e){
			
		}
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductResponseRest> searchById(Long id) {
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		try {
			//search product by id
			Optional<Product> product = productDao.findById(id);
			if(product.isPresent()) {
				byte[] imageDescompressed = Util.decompressZLib(product.get().getPicture());
				product.get().setPicture(imageDescompressed);
				list.add(product.get());
				response.getProduct().setProducts(list);
				response.setMetada("Respuesta ok", "00", "Producto encontrado");
			}else {
				response.setMetada("Respuesta ok", "-1", "Producto no encontrado");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
		}catch(Exception e){
			
		}
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductResponseRest> searchByName(String name) {
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		List<Product> listAux = new ArrayList<>();
		try {
			listAux = productDao.findByNameContainingIgnoreCase(name);
		
			if(listAux.size()>0) {
				listAux.stream().forEach((p)->{
					byte[] imageCompressed = Util.decompressZLib(p.getPicture());
					p.setPicture(imageCompressed);
					list.add(p);
				});
				
				response.getProduct().setProducts(list);
				response.setMetada("Respuesta ok", "00", "Productos encontrados");
			}else {
				response.setMetada("Respuesta ok", "-1", "No hay coincidencias");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e){
			
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<ProductResponseRest> deleteById(Long id) {
		ProductResponseRest response = new ProductResponseRest();
		try {
			productDao.deleteById(id);
			response.setMetada("Respuesta ok", "00", "Producto eliminado");
		}catch(Exception e) {
			response.setMetada("Respuesta ok", "-1", "Error al eliminar");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}


	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductResponseRest> search() {
		ProductResponseRest response = new ProductResponseRest();
		List<Product> list = new ArrayList<>();
		List<Product> listAux = new ArrayList<>();
		try {
			listAux = (List<Product>) productDao.findAll();
		
			if(listAux.size()>0) {
				listAux.stream().forEach((p)->{
					byte[] imageCompressed = Util.decompressZLib(p.getPicture());
					p.setPicture(imageCompressed);
					list.add(p);
				});
				
				response.getProduct().setProducts(list);
				response.setMetada("Respuesta ok", "00", "Productos encontrados");
			}else {
				response.setMetada("Respuesta ok", "-1", "No hay productos");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e){
			
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

}
