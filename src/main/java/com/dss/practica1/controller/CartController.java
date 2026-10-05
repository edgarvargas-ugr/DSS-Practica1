package com.dss.practica1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dss.practica1.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	private final CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@GetMapping
	public String getCart(Model model) {
		model.addAttribute("productos", cartService.getProductsCart());
		return "cart";
	}
	
	@GetMapping("/add/{id}")
	public String addCart(@PathVariable Long id, Model model) {
		cartService.addToCart(id);
		return "redirect:/cart";
	}
	
	@GetMapping("/remove/{id}")
	public String removeCart(@PathVariable Long id, Model model) {
		cartService.removeToCart(id);
		return "redirect:/cart";
	}
	
	
	
}
