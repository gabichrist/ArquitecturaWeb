package com.administrador.service;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.administrador.dto.CuentaDto;

import reactor.core.publisher.Flux;

@Service()
public class CuentaService implements BaseService<CuentaDto> {
	private final WebClient webClient;

	public CuentaService() {

		this.webClient = WebClient.create();
	}

	@Override
	public CuentaDto findById(Long id) throws Exception {
		return webClient.get().uri("http://localhost:8080/cuentas/" + id).retrieve().bodyToMono(CuentaDto.class)
				.block();
	}

	@Override
	public List<CuentaDto> findAll() throws Exception {
		Flux<CuentaDto> cuentas = webClient.get().uri("http://localhost:8080/cuentas/")
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(CuentaDto.class);
		return cuentas.collectList().block();
	}

	@Override
	public CuentaDto save(CuentaDto entity) throws Exception {
		throw new Error("To implement?");
//		if (paradaRepository.existsById(Long.valueOf(entity.getId())))
//			throw new ExpectableException("Ya existe una entidad con el id especificado");
//		return this.paradaRepository.save(entity);
	}

	@Override
	public CuentaDto update(Long id, CuentaDto entity) throws Exception {
		throw new Error("To implement?");
	}

	@Override
	public boolean delete(Long id) throws Exception {
		throw new Error("To implement?");
	}

	public CuentaDto anularCuentaUsuario(Long id) {
		return webClient.post().uri("http://localhost:8080/cuentas/" + id + "/anular").retrieve().bodyToMono(CuentaDto.class)
				.block();
	}
}
