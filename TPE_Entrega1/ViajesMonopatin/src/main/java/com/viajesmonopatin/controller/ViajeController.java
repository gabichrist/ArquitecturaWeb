package com.viajesmonopatin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viajesmonopatin.dto.ViajeMonopatinUsuarioDto;
import com.viajesmonopatin.exception.ExpectableException;
import com.viajesmonopatin.service.ViajeService;

@RestController
@RequestMapping("/viajes")
public class ViajeController {
	
	@Autowired
	private ViajeService viajeService;
	
	@GetMapping({"", "/"})
	public ResponseEntity<?> getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(viajeService.findAll());
		} catch (ExpectableException expectableException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(expectableException.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.viajeService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. No se encuentra el objeto buscado" + ".\"}");
		}
	}
	
	@GetMapping("/cantidad-viajes-anual")
	public ResponseEntity<?> getReportePorCantidadMinimaDeViajesAnual(
			@RequestParam(name = "anio", defaultValue = "") int anio,
			@RequestParam(name = "cantidad", defaultValue = "") Long cantidad){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.viajeService.obtenerReportePorCantidadMinimaDeViajesAnual(anio, cantidad));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. No se encuentra el objeto buscado" + ".\"}");
		}
	}
		
	@PostMapping("/iniciar")
	public ResponseEntity<?> iniciar(@RequestBody ViajeMonopatinUsuarioDto viajeMonopatinUsuarioDTO) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.viajeService.iniciarViaje(viajeMonopatinUsuarioDTO));
		} catch (ExpectableException expectableException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(expectableException.getMessage());	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. No se pudo iniciar el viaje" + ".\"}");
		}	
	}
	
	@PostMapping("/{id}/pausar")
	public ResponseEntity<?> pausar(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.viajeService.pausarViaje(id));
		} catch (ExpectableException expectableException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(expectableException.getMessage());	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. No se pudo pausar el viaje" + ".\"}");
		}	
	}
	
	@PostMapping("/{id}/reanudar")
	public ResponseEntity<?> reanudar(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.viajeService.reanudarViaje(id));
		} catch (ExpectableException expectableException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(expectableException.getMessage());	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. No se pudo reanudar el viaje" + ".\"}");
		}	
	}
	
	@PostMapping("/{id}/finalizar")
	public ResponseEntity<?> finalizar(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.viajeService.finalizarViaje(id));
		} catch (ExpectableException expectableException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(expectableException.getMessage());	
		} catch (Exception e) {
			e.getStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. No se pudo finalizar el viaje" + ".\"}");
		}	
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.viajeService.delete(id));
		} catch (ExpectableException expectableException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{\"error\":\"" + expectableException.getMessage() + "\"}");
		} catch (Exception e2) {
			System.out.println("error " + e2.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}

	//@PutMapping("/{id}")

	@GetMapping("/facturacion/anio/{anio}")
	public ResponseEntity<?> obtenerFacturacion(
			@PathVariable("anio") int anio,
			@RequestParam(name = "mes-desde", defaultValue = "1") int mesDesde,
			@RequestParam(name = "mes-hasta", defaultValue = "12") int mesHasta
			) {
		if (mesDesde < 1 || mesDesde > 12)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("mes-desde debe ser un entero entre 1 y 12");
		if (mesHasta < 1 || mesHasta > 12)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("mes-hasta debe ser un entero entre 1 y 12");
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(
					viajeService.obtenerFacturacionAnual(anio, mesDesde, mesHasta)
					);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error al procesar el reporte" + ".\"}");
		}
	}	

}
