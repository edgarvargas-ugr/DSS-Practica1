package com.dss.practica1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dss.practica1.model.Producto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {
	
	private final Map<String, Map<Long, Integer>> carts;
	private final ProductService productService;
	
	public CartService(Map<String, Map<Long, Integer>> carts, ProductService productService) {
		this.carts = carts;
		this.productService = productService;
	}
	
	public  Map<Long, Integer> getIdsCart(String user) {
		return carts.get(user);
	}
	
	public Map<Producto, Integer> getProductsCart(String user){
		Map<Long, Integer> idproductoscantidades = carts.get(user);
	    if (idproductoscantidades == null || idproductoscantidades.isEmpty())
	        return Map.of();

	    return productService.getProductsByIds(new ArrayList<>(idproductoscantidades.keySet())).stream()
	            .collect(Collectors.toMap(
	                    p -> p,
	                    p -> idproductoscantidades.get(p.getId())));
	}
	
	public void addToCart(String user, Long idProducto) {
		Map<Long, Integer> cart = carts.get(user);
		
		if(cart == null) {
			cart = new HashMap<Long, Integer>();
	        cart.put(idProducto, 1);
	        carts.put(user, cart);
		}else {
			Integer cantidad = cart.get(idProducto);
	        cart.put(idProducto, cantidad == null ? 1 : cantidad + 1);
		}
		
		
	}
	
	public void removeToCart(String user, Long idProducto) {
		Map<Long, Integer> cart = carts.get(user);
		
		if(cart != null) {
			Integer cantidad = cart.get(idProducto);
			if(cantidad != null && cantidad > 1) {
				cart.put(idProducto, cantidad - 1);
			}else {
				cart.remove(idProducto);
			}
		}
	}
	
}
