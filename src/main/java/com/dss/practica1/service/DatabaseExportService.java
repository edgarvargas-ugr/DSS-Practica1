package com.dss.practica1.service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dss.practica1.repository.ProductRepo;

@Service
public class DatabaseExportService {
	
	private final ProductRepo productRepo;
	
	private final JdbcTemplate jdbc;
	
	public DatabaseExportService(ProductRepo productRepo, JdbcTemplate jdbc) {
		this.productRepo = productRepo;
		this.jdbc = jdbc;
	}
	
	
	public byte[] exportDatabaseToSql() { 
		/*StringBuilder sqlInserts = new StringBuilder();
		productRepo.findAll().forEach(producto -> {
			sqlInserts.append("INSERT INTO Product (id, nombre, precio) VALUES ("+ producto.getId() + ",'" + producto.getNombre().replace("'","''") +"','"+producto.getPrecio()+"'); \n");
		});
		
		return sqlInserts.toString().getBytes();*/
		
		List<String> lineas = jdbc.queryForList("SCRIPT NOSETTINGS TABLE PRODUCTO", String.class);
	    return String.join("\n", lineas).getBytes(StandardCharsets.UTF_8);
	}
}
