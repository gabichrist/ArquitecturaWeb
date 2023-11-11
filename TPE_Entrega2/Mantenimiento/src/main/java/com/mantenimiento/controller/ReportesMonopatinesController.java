package com.mantenimiento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mantenimiento.service.MonopatinUsoService;

@RestController
@RequestMapping("/mantenimientos/reportes/monopatines")
public class ReportesMonopatinesController {	
	
	@Autowired
	private MonopatinUsoService monopatinUsoService;
	
	@GetMapping({"", "/"})
	public ResponseEntity<?> getReporteUso() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(monopatinUsoService.reporteUso());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@GetMapping({"kilometros", "kilometros/"})
	public ResponseEntity<?> getReporteKilometros(
			@RequestParam(name = "con-tiempo-de-pausa", defaultValue = "true") boolean incluirTiempoDePausa
			) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(monopatinUsoService.reporteKilometros(incluirTiempoDePausa));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@GetMapping({"tiempo-con-pausas", "tiempo-con-pausas/"})
	public ResponseEntity<?> getReporteTiempoConPausas() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(monopatinUsoService.reporteTiempoConPausas());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
	@GetMapping({"tiempo-sin-pausas", "tiempo-sin-pausas/"})
	public ResponseEntity<?> getReporteTiempoSinPausas() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(monopatinUsoService.reporteTiempoSinPausas());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
	
}

