package com.viajesmonopatin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.viajesmonopatin.utils.CargaDeDatos;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ViajesMonopatinApplication {
	
	@Autowired
	private CargaDeDatos cargaDeDatos;

	public static void main(String[] args) {
		SpringApplication.run(ViajesMonopatinApplication.class, args);
	}
	
	@PostConstruct
	public void init() throws NumberFormatException, Exception{
		cargaDeDatos.cargarTarifasDesdeCSV();
		cargaDeDatos.cargarParadasDesdeCSV();
		cargaDeDatos.cargarMonopatinesDesdeCSV();
		cargaDeDatos.cargarViajesDesdeCSV();
	}
}