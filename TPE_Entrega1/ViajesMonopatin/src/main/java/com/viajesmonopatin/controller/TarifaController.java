package com.viajesmonopatin.controller;

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


import com.viajesmonopatin.exception.ExpectableException;
import com.viajesmonopatin.model.Tarifa;
import com.viajesmonopatin.service.TarifaService;

@RestController
@RequestMapping("/tarifas")

public class TarifaController {
	@Autowired
	private TarifaService tarifaService;
	
	@GetMapping({"", "/"})
	public ResponseEntity<?> getAll(
			@RequestParam(name = "orden", defaultValue = "id") String sortBy
			){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(tarifaService.findAll(sortBy));
		}catch(ExpectableException e){
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(tarifaService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }
	
	@PostMapping({"", "/"})
	public ResponseEntity<?> save(@RequestBody Tarifa e){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.tarifaService.save(e));
		} catch (Exception e2) {
			System.out.println("error " + e2.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.tarifaService.delete(id));
		} catch (ExpectableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e2) {
			System.out.println("error " + e2.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Tarifa tarifa){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.tarifaService.update(id, tarifa));
		} catch (Exception e2) {
			System.out.println("error " + e2.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
}
