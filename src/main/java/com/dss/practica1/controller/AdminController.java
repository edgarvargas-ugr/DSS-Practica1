package com.dss.practica1.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dss.practica1.service.DatabaseExportService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final DatabaseExportService databaseExportService;
	
	public AdminController(DatabaseExportService databaseExportService) {
		this.databaseExportService = databaseExportService;
	}
	
	@GetMapping("/export")
	public ResponseEntity<byte[]> exportar() {
	    byte[] contenido = databaseExportService.exportDatabaseToSql();

	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=productos.sql")
	            .contentType(MediaType.TEXT_PLAIN)
	            .body(contenido);
	}

}
