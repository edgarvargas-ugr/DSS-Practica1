package com.dss.practica1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dss.practica1.model.*;

import com.dss.practica1.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {
	
	private final ProductService productService;
    
    public ProductRestController(ProductService productService) {
    	this.productService = productService;
    }

    @GetMapping
    public List<Producto> getAllProducts() {
        return productService.getAllProducts();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductID(@PathVariable Long id) {
    	Producto p = productService.getProductById(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<Producto> postProducto(@RequestBody Producto producto) {
        Producto guardado = productService.saveProduct(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Producto> putProducto(@RequestBody Producto producto, @PathVariable Long id) {
    	producto.setId(id);
    	return productService.updateProduct(producto, id) ? ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id) ? ResponseEntity.accepted().build() : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/busqueda")
    public List<Producto> busquedaProductos(@RequestParam(required = false) String nombre, @RequestParam(required = false) Double precioMenor, @RequestParam(required = false) Double precioMayor, Model model) {
    	if(nombre!= null) {
    		return productService.getProductSearchName(nombre);
    	}
    	else if(precioMenor!= null || precioMayor!= null) {
    		return productService.getProductRangePrice(precioMenor, precioMayor);
    	}
    	else {
    		return productService.getAllProducts();
    	}
    }

}
