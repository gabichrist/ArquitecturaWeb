package com.usuario.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.usuario.model.Cuenta;
import com.usuario.model.Usuario;
import com.usuario.repository.CuentaRepository;
import com.usuario.repository.UsuarioRepository;

import enums.Roles;

@Component
public class CargaDeDatos {
	
	private final UsuarioRepository usuarioRepository;
	private final CuentaRepository cuentaRepository;
	
	
	
	public CargaDeDatos(UsuarioRepository usuarioRepository, CuentaRepository cuentaRepository) {
		this.usuarioRepository = usuarioRepository;
		this.cuentaRepository = cuentaRepository;
	}
	
	 public void cargarUsuariosDesdeCSV() throws IOException {
	        File archivoCSV = ResourceUtils.getFile("src/main/java/com/usuario/csv/usuarios_cuenta.csv");

	        try (FileReader reader = new FileReader(archivoCSV);
	             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
	        	
	            for (CSVRecord csvRecord : csvParser) {
	                Usuario u = new Usuario();
	                u.setId(Long.parseLong(csvRecord.get("id")));
	                u.setNombre(csvRecord.get("nombre"));
	                u.setApellido(csvRecord.get("apellido"));
	                u.setNro_celular(csvRecord.get("nro_celular"));
	                
	                u.setEmail(csvRecord.get("email"));
	                switch (csvRecord.get("rol")) {
					case "USER":
						u.setRol(Roles.USER);
						break;
					case "ADMIN":
						u.setRol(Roles.ADMIN);
						break;
					case "MAINTAINER":
						u.setRol(Roles.MAINTAINER);
						break;
					default:
						System.out.println("nada " + csvRecord.get("roles"));
						break;
					}
	                Cuenta c = new Cuenta();
	                c.setFecha_alta(Timestamp.valueOf(csvRecord.get("fecha_alta")));
	                c.setId_mercado_pago(Integer.parseInt(csvRecord.get("id_mercado_pago")));
	                c.setSaldo(Float.parseFloat(csvRecord.get("saldo")));
	                c.setHabilitada(true);
	                c.addUsuario(u);
	                u.addCuenta(c);
	                usuarioRepository.save(u);
	            }
	        }
	    }

}
