package com.mantenimiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mantenimiento.utils.CargaDeDatos;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class MantenimientoApplication {
	
	@Autowired
	private CargaDeDatos cargaDeDatos;

	public static void main(String[] args) {
		SpringApplication.run(MantenimientoApplication.class, args);
	}
	
	@PostConstruct
	public void init() throws NumberFormatException, Exception {
		cargaDeDatos.cargarMantenimientosDesdeCSV();
	}
}
