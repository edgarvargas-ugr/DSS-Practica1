package com.dss.practica1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.dss.practica1.service.ProductService;

import lombok.AllArgsConstructor;

import com.dss.practica1.model.Producto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.ui.Model;


@Controller
@AllArgsConstructor 
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;
    
    public ProductController(ProductService _productService) {
    	this.productService = _productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("productos", productService.getAllProducts());
        return "productos";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable String id, Model model) {
        model.addAttribute("productos", productService.getProductById(Long.parseLong(id)));
        return "productos";
    }

    @GetMapping({"/formulario-producto", "/formulario-producto/{id}"})
    public String getFormularioProducto(@PathVariable(required = false) Long id , Model model) {
    	
    	Producto producto = (id != null) ? productService.getProductById(id) : null;

        if (id != null && producto == null) {
            model.addAttribute("isProducto", false); 
        }

        model.addAttribute("producto", producto != null ? producto : new Producto());
        return "formulario-producto";
    }
    
    @PostMapping
    public String postProducto(@ModelAttribute Producto producto) {
        productService.saveProduct(producto);
        return "redirect:/products";
    }
    
    @PutMapping
    public String putProducto(@ModelAttribute Producto producto) {
    	return productService.updateProduct(producto, producto.getId()) ? "redirect:/products" : "formulario-producto";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProduct(Long.parseLong(id));
        return "redirect:/productos";
    }
}
