package com.dss.practica1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dss.practica1.model.Producto;
import com.dss.practica1.repository.ProductRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepo productRepo;
    
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Producto> getAllProducts() {
        return productRepo.findAll();
    }

    public Producto getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }
    
    public List<Producto> getProductsByIds(List<Long> ids){
    	return productRepo.findAllById(ids);
    }

    public Producto saveProduct(Producto product) {
        return productRepo.save(product);
    }
    
    public boolean updateProduct(Producto product, Long id) {
    	Optional<Producto> currentProduct = productRepo.findById(id);
    	
    	if(currentProduct.isEmpty()) {
    		return false;
    	}
    	
    	//currentProduct.get().setNombre(product.getNombre());
		//currentProduct.get().setPrecio(product.getPrecio());
		
		productRepo.save(product);
		
    	return true;
    }

    public boolean deleteProduct(Long id) {
    	
    	Optional<Producto> currentProduct = productRepo.findById(id);
    	
    	if(currentProduct.isEmpty()) {
    		return false;
    	}
        productRepo.deleteById(id);
        return true;
    }
    
    public List<Producto> getProductSearchName(String name){
    	return productRepo.findByNombreContainingIgnoreCase(name);
    }
    
    public List<Producto> getProductRangePrice(Double precioMenor, Double precioMayor){
    	if(precioMenor == null && precioMayor != null) {
    		return productRepo.findByPrecioLessThanEqual(precioMayor);
    	}else if(precioMenor != null && precioMayor == null) {
    		return productRepo.findByPrecioGreaterThanEqual(precioMenor);
    	}
    	
    	return productRepo.findByPrecioBetween(precioMenor, precioMayor);
    }
}
