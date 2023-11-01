package com.usuario.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.exception.ExpectableException;
import com.usuario.model.Cuenta;
import com.usuario.model.Usuario;
import com.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping({ "", "/" })
	public ResponseEntity<?> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
		} catch (ExpectableException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. No se encuentra el objeto buscado" + ".\"}");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.delete(id));
		} catch (ExpectableException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e2) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Usuario usuario) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.update(id, usuario));
		} catch (Exception e2) {
			System.out.println("error " + e2.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@PostMapping("/{id}/cuentas")
	public ResponseEntity<?> agregarCuentaUsuario(@PathVariable Long id, @RequestBody Cuenta cuenta) {
		try {
			if (cuenta.getFecha_alta() == null) {
				cuenta.setFecha_alta(new Timestamp(System.currentTimeMillis()));
			}
			return ResponseEntity.status(HttpStatus.OK).body(usuarioService.agregarCuentaUsuario(id, cuenta));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}

	// ver validacion de mail?
	@PostMapping({ "", "/" })
	public ResponseEntity<?> save(@RequestBody Usuario usuario) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.save(usuario));
		} catch (Exception e2) {
			System.out.println("error " + e2.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}

	@GetMapping("/monopatines/en-la-zona")
	public ResponseEntity<?> getMonopatinesEnLaZona(
			@RequestParam(name = "latitud", defaultValue = "") Float latitud, 
			@RequestParam (name = "longitud", defaultValue = "") Float longitud){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getMonopatinesEnLaZona(latitud, longitud));
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. No se encuentra el objeto buscado" + ".\"}");
		}
	}
}
