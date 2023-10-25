package com.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.usuario.model.Usuario;
import com.usuario.repository.UsuarioRepository;

public class UsuarioService implements BaseService<Usuario>{

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario save(Usuario entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario update(Long id, Usuario entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	//login, registrarse
	
}
