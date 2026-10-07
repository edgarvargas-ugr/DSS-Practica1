package com.dss.practica1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dss.practica1.model.Producto;

public interface ProductRepo extends JpaRepository<Producto, Long> {
	
	List<Producto> findByNombreContainingIgnoreCase(String nombre);
	List<Producto> findByPrecioBetween(double precio1, double precio2);
	List<Producto> findByPrecioLessThanEqual(double precio1);
	List<Producto> findByPrecioGreaterThanEqual(double precio1);
	
}
