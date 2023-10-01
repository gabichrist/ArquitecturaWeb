package com.example.entregable3;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entregable3.utils.CargaDeDatos;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Entregable31Application {

	@Autowired
	private CargaDeDatos cargaDeDatos;

	
	public static void main(String[] args) {
		SpringApplication.run(Entregable31Application.class, args);
	}

	@PostConstruct
	public void init() throws IOException {
		cargaDeDatos.cargarCarrerasDesdeCSV();
		cargaDeDatos.cargarEstudiantesDesdeCSV();
	}

}
