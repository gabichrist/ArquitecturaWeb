package com.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.usuario.utils.CargaDeDatos;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class UsuarioApplication {
	
	@Autowired
	private CargaDeDatos cargaDeDatos;

	public static void main(String[] args) {
		SpringApplication.run(UsuarioApplication.class, args);
	}
	
	@PostConstruct
	public void init() throws NumberFormatException, Exception {
		cargaDeDatos.cargarUsuariosDesdeCSV();
	}

}
