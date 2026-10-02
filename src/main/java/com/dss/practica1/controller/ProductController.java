package com.dss.practica1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.dss.practica1.service.ProductService;

import lombok.AllArgsConstructor;

import com.dss.practica1.model.Producto;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor 
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("productos", productService.getAllProducts());
        return "productos";
    }

    @GetMapping("/{id}")
    public String getMethodName(@PathVariable String id, Model model) {
        model.addAttribute("productos", productService.getProductById(Long.parseLong(id)));
        return "productos";
    }

    @GetMapping("/formulario-producto")
    public String getMethodName(Model model) {
        model.addAttribute("producto", new Producto());
        return "formulario-producto";
    }
    
    @PostMapping
    public String postMethodName(@ModelAttribute Producto producto) {
        System.out.println("Recibido: " + producto.getNombre() + " - " + producto.getPrecio());
        productService.saveProduct(producto);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String deleteMethodName(@PathVariable String id) {
        return productService.deleteProduct(Long.parseLong(id));
    }
}
