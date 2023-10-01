package com.example.entregable3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entregable3.model.Estudiante;
import com.example.entregable3.service.EstudianteServicio;

@RestController
// @RequestMapping(value = "estudiantes", consumes = "application/json", produces = "application/json")
@RequestMapping("/estudiantes")
//@Api(value = "EstudianteController", description = "REST API Estudiante descripcion")
public class EstudianteController {

	// @Qualifier("estudianteServicio")
	@Autowired
	private EstudianteServicio estudianteServicio;

	public EstudianteController(@Qualifier("estudianteServicio") EstudianteServicio estudianteServicio) {
		this.estudianteServicio = estudianteServicio;
	}

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServicio.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteServicio.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }
	
	@PostMapping()
	public ResponseEntity<?> newEstudiante(@RequestBody Estudiante e) {
		try {
			System.out.println("Estudiante e" + e);
//			return ResponseEntity.status(HttpStatus.OK).body(this.estudianteServicio.save(e));
			return ResponseEntity.status(200).body("true");
		} catch (Exception e2) {
			System.out.println("error " + e2.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}

	@GetMapping("/test")
	public ResponseEntity<Boolean> test() {
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}

	@GetMapping("/test2")
	public ResponseEntity<String> test2() {
		return ResponseEntity.status(HttpStatus.OK).body("funcionando");
	}

}
