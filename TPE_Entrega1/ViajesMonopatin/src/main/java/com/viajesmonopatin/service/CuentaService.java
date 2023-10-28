package com.viajesmonopatin.service;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.viajesmonopatin.dto.Cuenta;

import reactor.core.publisher.Flux;

@Service("cuentaServicio")
public class CuentaService implements BaseService<Cuenta> {
    
	private final WebClient webClient;

    public CuentaService() {    	
        this.webClient = WebClient.create();
    }
    
	@Override
	public Cuenta findById(Long id) throws Exception {
	      return webClient.get()
	                .uri("http://localhost:8080/cuentas/" + id)
	                .retrieve()
	                .bodyToMono(Cuenta.class)
	                .block();
	}
	
	@Override
	public List<Cuenta> findAll() throws Exception {
		Flux<Cuenta> cuentas = webClient.get()
	                .uri("http://localhost:8080/cuentas/")
	                .accept(MediaType.APPLICATION_JSON)
	                .retrieve()
	                .bodyToFlux(Cuenta.class);
	    return cuentas.collectList().block();
	}

	@Override
	public Cuenta save(Cuenta entity) throws Exception {
		throw new Error("To implement?");
//		if (paradaRepository.existsById(Long.valueOf(entity.getId())))
//			throw new ExpectableException("Ya existe una entidad con el id especificado");
//		return this.paradaRepository.save(entity);
	}

	@Override
	public Cuenta update(Long id, Cuenta entity) throws Exception {
		throw new Error("To implement?");
	}

	@Override
	public boolean delete(Long id) throws Exception {
		throw new Error("To implement?");
	}
}
