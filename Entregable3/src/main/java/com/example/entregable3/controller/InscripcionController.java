package com.example.entregable3.controller;

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

import com.example.entregable3.dtos.InscripcionDTO;
import com.example.entregable3.exception.ExpectableException;
import com.example.entregable3.service.InscripcionServicio;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

	@Autowired
	private InscripcionServicio inscripcionServicio;


    @GetMapping({"", "/"})
    public ResponseEntity<?>  getAll() {
        try{
        	System.out.println(inscripcionServicio.findAll());
            return ResponseEntity.status(HttpStatus.OK).body(inscripcionServicio.findAll());
        }catch (ExpectableException e){
        	return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{luEstudiante}/{idCarrera}")
    public ResponseEntity<?>getOne(@PathVariable Long luEstudiante, @PathVariable Long idCarrera){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(inscripcionServicio.findById(luEstudiante, idCarrera));
        }catch (ExpectableException e){
        	return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }
	
	@DeleteMapping("/{luEstudiante}/{idCarrera}")
	public ResponseEntity<?> delete(@PathVariable Long luEstudiante, @PathVariable Long idCarrera){
		try {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.inscripcionServicio.delete(luEstudiante, idCarrera));
		} catch (ExpectableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e2) {
			System.out.println("error " + e2.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@PostMapping({"", "/"})
	public ResponseEntity<?> save(@RequestBody InscripcionDTO inscripcionDto){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.inscripcionServicio.save(inscripcionDto));
		} catch (ExpectableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e2) {
			System.out.println("error " + e2.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@PutMapping("/{luEstudiante}/{idCarrera}")
	public ResponseEntity<?> save(@PathVariable Long luEstudiante, @PathVariable Long idCarrera, @RequestBody InscripcionDTO inscripcionDto){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.inscripcionServicio.update(luEstudiante, idCarrera, inscripcionDto));
		} catch (ExpectableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e2) {
			System.out.println("error " + e2.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
}
