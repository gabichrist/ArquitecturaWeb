package com.administrador.controller;

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

import com.administrador.dto.FacturacionAnualDto;
import com.administrador.dto.TarifaDto;
import com.administrador.exception.ExpectableException;
import com.administrador.service.AdministradorService;

@RestController
@RequestMapping("admin")
public class AdministradorController {

	@Autowired
	private AdministradorService adminService;

	@PostMapping("/anular-cuenta/{id}")
	public ResponseEntity<?> anularCuentaUsuario(@PathVariable Long id) throws ExpectableException {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(adminService.anularCuentaUsuario(id));
		} catch (Exception e2) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}

	@GetMapping("/monopatines/disponibilidad")
	public ResponseEntity<?> obtenerReporteDisponibilidadMonopatines() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(adminService.obtenerDisponibilidadMonopatines());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@GetMapping("/facturacion/anio/{anio}")
	public ResponseEntity<?> obtenerFacturacionAnual(
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
					adminService.obtenerFacturacion(anio, mesDesde, mesHasta)
				);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error al procesar el reporte" + ".\"}");
		}
	}
	
	@GetMapping("/viajes/cantidad-viajes-anual")
	public ResponseEntity<?> obtenerReporteMonopatinesPorCantidadDeViajesAnual(
			@RequestParam(name = "anio", defaultValue = "") int anio,
			@RequestParam(name = "cantidad", defaultValue = "") Long cantidad){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(adminService.obtenerReportePorCantidadMinimaDeViajesAnual(anio, cantidad));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. No se encuentra el objeto buscado" + ".\"}");
		}
	}
	
	
	@PostMapping("/viajes/ajustar-tarifas")
	public ResponseEntity<?> ajustarPrecios(@RequestBody TarifaDto tarifa){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(adminService.ajustarPrecios(tarifa));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@DeleteMapping("/paradas/quitar/{id}")
	public ResponseEntity<?> quitarParada(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.adminService.quitarParada(id));
		} catch (Exception e2) {		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
}
