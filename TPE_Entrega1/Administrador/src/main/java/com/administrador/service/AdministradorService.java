package com.administrador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administrador.dto.CuentaDto;

@Service
public class AdministradorService {

	@Autowired 
	private CuentaService cuentaService;
	
	public CuentaDto anularCuentaUsuario(Long id) {
		return this.cuentaService.anularCuentaUsuario(id);
	}
}
