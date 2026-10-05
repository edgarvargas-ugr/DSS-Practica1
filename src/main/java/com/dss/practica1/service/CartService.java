package com.dss.practica1.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.dss.practica1.model.Producto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {
	
	private final List<Long> idProductos;
	private final ProductService productService;
	
	public CartService(List<Long> idProductos, ProductService productService) {
		this.idProductos = idProductos;
		this.productService = productService;
	}
	
	public List<Long> getIdsCart() {
		return idProductos;
	}
	
	public List<Producto> getProductsCart(){
		return productService.getProductsByIds(this.idProductos);
	}
	
	public void addToCart(Long idProducto) {
		idProductos.add(idProducto);
	}
	
	public void removeToCart(Long idProducto) {
		idProductos.remove(idProducto);
	}
	
}
