package com.usuario.service;

import java.util.List;
import org.springframework.web.reactive.function.client.WebClient;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.usuario.dtos.MonopatinDto;

import reactor.core.publisher.Flux;

@Service
public class ViajesMonopatinService {
	
	private final WebClient webClient;
	private final String baseUrl = "http://localhost:8081";
	
	public ViajesMonopatinService() {
		this.webClient = WebClient.create(this.baseUrl);
	}
	
	public List<MonopatinDto> getMonopatinesEnLaZona(Float latitud, Float longitud){
		Flux<MonopatinDto> monopatines = webClient.get()
				.uri(uriBuilder -> uriBuilder
						.path("/monopatines/disponibles/latitud/"+ latitud + "/longitud/" + longitud)
						.queryParam("latitud", latitud)
						.queryParam("longitud", longitud)
						.build()
						)
				
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(MonopatinDto.class);
		return monopatines.collectList().block();
	}
}
