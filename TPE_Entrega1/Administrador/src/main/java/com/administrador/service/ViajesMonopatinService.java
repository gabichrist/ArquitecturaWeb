package com.administrador.service;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.administrador.dto.MonopatinDto;

import reactor.core.publisher.Flux;

@Service
public class ViajesMonopatinService {

	
	private final WebClient webClient;

	public ViajesMonopatinService() {

		this.webClient = WebClient.create();
	}

	public List<MonopatinDto> obtenerMonopatinesOperativos(){
		Flux<MonopatinDto> monopatines = webClient.get().uri("http://localhost:8081/monopatines/operativos")
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(MonopatinDto.class);
		return monopatines.collectList().block();
	}
	
	public List<MonopatinDto> obtenerMonopatinesEnMantenimiento(){
		Flux<MonopatinDto> monopatines = webClient.get().uri("http://localhost:8081/monopatines/en-mantenimiento")
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(MonopatinDto.class);
		return monopatines.collectList().block();
	}
}
