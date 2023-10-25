package com.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.usuario.model.Cuenta;
import com.usuario.repository.CuentaRepository;

public class CuentaService implements BaseService<Cuenta>{

	@Autowired
	private CuentaRepository cuentaRepository;
	
	@Override
	public List<Cuenta> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta save(Cuenta entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta update(Long id, Cuenta entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	//anular cuenta

}
