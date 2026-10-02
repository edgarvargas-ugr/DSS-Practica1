package com.dss.practica1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dss.practica1.model.Producto;
import com.dss.practica1.repository.ProductRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepo productRepo;

    public List<Producto> getAllProducts() {
        return productRepo.findAll();
    }

    public Producto getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public Producto saveProduct(Producto product) {
        return productRepo.save(product);
    }

    public String deleteProduct(Long id) {
        productRepo.deleteById(id);
        return id.toString();
    }
}
