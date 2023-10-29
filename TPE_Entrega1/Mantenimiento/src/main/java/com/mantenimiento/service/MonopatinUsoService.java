package com.mantenimiento.service;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mantenimiento.dto.MonopatinReporteKilometros;
import com.mantenimiento.dto.MonopatinReporteKilometrosConTiempoPausa;
import com.mantenimiento.dto.MonopatinUsoDto;

import reactor.core.publisher.Flux;


@Service
public class MonopatinUsoService {
	
	private final WebClient webClient;

	public MonopatinUsoService() {
		this.webClient = WebClient.create();
	}

	public List<?> reporteKilometros(boolean incluirTiempoDePausa){
		if (!incluirTiempoDePausa) {
			Flux<MonopatinReporteKilometros> monopatines = webClient.get().uri("http://localhost:8081/monopatines")
					.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(MonopatinReporteKilometros.class);
			return monopatines.collectList().block();
		} 

		Flux<MonopatinUsoDto> monopatines = webClient.get().uri("http://localhost:8081/monopatines")
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(MonopatinUsoDto.class);
		return monopatines.map(
			    m -> new MonopatinReporteKilometrosConTiempoPausa(m)
			).collectList().block();

	}
	
	public List<MonopatinUsoDto> reporteUso(){
		Flux<MonopatinUsoDto> monopatines = webClient.get().uri("http://localhost:8081/monopatines")
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(MonopatinUsoDto.class);
		return monopatines.collectList().block();
	}
}
