package com.administrador.service;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.administrador.dto.MonopatinDto;
import com.administrador.dto.TarifaDto;
import com.administrador.dto.ViajeMonopatinDto;

import reactor.core.publisher.Flux;

@Service
public class ViajesMonopatinService {

	private final WebClient webClient;
	private final String baseUrl = "http://localhost:8081";
	
	public ViajesMonopatinService() {

		this.webClient = WebClient.create(this.baseUrl);
	}

	public List<MonopatinDto> obtenerMonopatinesOperativos(){
		Flux<MonopatinDto> monopatines = webClient.get().uri("/monopatines/operativos")
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(MonopatinDto.class);
		return monopatines.collectList().block();
	}
	
	public List<MonopatinDto> obtenerMonopatinesEnMantenimiento(){
		Flux<MonopatinDto> monopatines = webClient.get().uri("/monopatines/en-mantenimiento")
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(MonopatinDto.class);
		return monopatines.collectList().block();
	}
	
	public TarifaDto crearTarifa(TarifaDto tarifa) {
		TarifaDto tarifaMonopatin = webClient.post().uri("/tarifas").body(BodyInserters.fromValue(tarifa))
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(TarifaDto.class).blockFirst();
		return tarifaMonopatin;
	}
	
	public List<ViajeMonopatinDto> obtenerPorCantidadMinimaDeViajesAnual(int anio, Long cantidad) {
		Flux<ViajeMonopatinDto> monopatines = webClient.get()
				.uri(uriBuilder -> uriBuilder
				.path("/viajes/cantidad-viajes-anual")
				.queryParam("anio", anio)
				.queryParam("cantidad", cantidad)
				.build())
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(ViajeMonopatinDto.class);
		return monopatines.collectList().block();
	}
}
