package com.viajesmonopatin.service;

import java.util.List;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.viajesmonopatin.dto.CuentaDto;
import com.viajesmonopatin.dto.ModificarSaldoDto;

import reactor.core.publisher.Flux;

@Service("cuentaServicio")
public class CuentaService implements BaseService<CuentaDto> {
    
	private final WebClient webClient;

    public CuentaService() {    	
        this.webClient = WebClient.create();
    }
    
	@Override
	public CuentaDto findById(Long id) throws Exception {
		try {
			return webClient.get()
					.uri("http://localhost:8080/cuentas/" + id)
					.retrieve()
					.bodyToMono(CuentaDto.class)
					.block();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception(e);
		}
	}
	
	@Override
	public List<CuentaDto> findAll() throws Exception {
		Flux<CuentaDto> cuentas = webClient.get()
	                .uri("http://localhost:8080/cuentas/")
	                .accept(MediaType.APPLICATION_JSON)
	                .retrieve()
	                .bodyToFlux(CuentaDto.class);
	    return cuentas.collectList().block();
	}

	@Override
	public CuentaDto save(CuentaDto entity) throws Exception {		
		CuentaDto cuenta = webClient.post().uri("http://localhost:8080/cuentas/").body(BodyInserters.fromValue(entity))
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(CuentaDto.class).blockFirst();
		return cuenta;	
	}

	public CuentaDto descontarSaldo(Long id, Float importe) throws Exception {
		ModificarSaldoDto dto = new ModificarSaldoDto();
		dto.setImporte(importe);
		CuentaDto cuenta = webClient.post().uri("http://localhost:8080/cuentas/" + id + "/descontar-saldo").body(BodyInserters.fromValue(dto))
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(CuentaDto.class).blockFirst();
		return cuenta;	
	}

	@Override
	public CuentaDto update(Long id, CuentaDto entity) throws Exception {
		throw new Error("To implement?");
	}

	@Override
	public boolean delete(Long id) throws Exception {
		throw new Error("To implement?");
	}
}
