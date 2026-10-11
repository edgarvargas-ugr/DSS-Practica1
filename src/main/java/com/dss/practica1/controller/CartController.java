package com.dss.practica1.controller;

import java.security.Principal;

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
	public String getCart(Principal user, Model model) {
		model.addAttribute("productos", cartService.getProductsCart(user.getName()));
		return "cart";
	}
	
	@GetMapping("/add/{id}")
	public String addCart(Principal user, @PathVariable Long id, Model model) {
		cartService.addToCart(user.getName(), id);
		return "redirect:/cart";
	}
	
	@GetMapping("/remove/{id}")
	public String removeCart(Principal user, @PathVariable Long id, Model model) {
		cartService.removeToCart(user.getName(), id);
		return "redirect:/cart";
	}
	
	
	
}
