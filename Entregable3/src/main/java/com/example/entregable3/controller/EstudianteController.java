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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entregable3.exception.ExpectableException;
import com.example.entregable3.model.Estudiante;
import com.example.entregable3.service.EstudianteServicio;

@RestController
@RequestMapping("/estudiantes")
//@Api(value = "EstudianteController", description = "REST API Estudiante descripcion")
public class EstudianteController {

	// @Qualifier("estudianteServicio")
	@Autowired
	private EstudianteServicio estudianteServicio;


    @GetMapping({"", "/"})
    public ResponseEntity<?>  getAll(
    		@RequestParam(name = "orden", defaultValue = "lu") String sortBy,
    		@RequestParam(name = "genero", defaultValue = "") String genre
    		) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServicio.findAll(sortBy, genre));
        }catch (ExpectableException e){
        	return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServicio.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }
    
    @GetMapping({"/carrera-ciudad"})
    public ResponseEntity<?> getPorCarreraYCiudad( 
    		@RequestParam(name = "carrera", defaultValue = "") int carrera,
    		@RequestParam(name = "ciudad", defaultValue = "") String ciudad    	
    		){
    	try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServicio.findByCarreraYCiudad(carrera, ciudad));
		} catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
	                    ".\"}");
		}
    }
	
	@PostMapping({"", "/"})
	public ResponseEntity<?> save(@RequestBody Estudiante e){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.estudianteServicio.save(e));
		} catch (Exception e2) {
			System.out.println("error " + e2.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.estudianteServicio.delete(id));
		} catch (ExpectableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e2) {
			System.out.println("error " + e2.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Estudiante estudiante){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.estudianteServicio.update(id, estudiante));
		} catch (Exception e2) {
			System.out.println("error " + e2.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
}
