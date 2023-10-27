package com.mantenimiento.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.mantenimiento.exception.ExpectableException;
import com.mantenimiento.model.Mantenimiento;
import com.mantenimiento.service.MantenimientoService;

@RestController
@RequestMapping("/mantenimientos")
public class MantenimientoController {	
	
	@Autowired
	private MantenimientoService mantenimientoService;
	
	@GetMapping({"", "/"})
	public ResponseEntity<?> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(mantenimientoService.findAll());
		} catch (ExpectableException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(mantenimientoService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            		.body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.mantenimientoService.delete(id));
		} catch (ExpectableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            		.body(e.getMessage());
		} catch (Exception e2) {		
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@PostMapping({"", "/"})
	public ResponseEntity<?> save(@RequestBody Mantenimiento mantenimiento){
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.mantenimientoService.save(mantenimiento));
		} catch (Exception e) {		
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Mantenimiento mantenimiento){
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(this.mantenimientoService.update(id, mantenimiento));
		} catch (Exception e) {		
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
}

