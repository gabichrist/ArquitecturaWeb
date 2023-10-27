package com.usuario.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.exception.ExpectableException;
import com.usuario.model.Usuario;
import com.usuario.repository.UsuarioRepository;

@Service("usuarioServicio")
public class UsuarioService implements BaseService<Usuario> {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> findAll() throws Exception {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario findById(Long id) throws Exception {
		try {
			Optional<Usuario> usuarioBuscado = usuarioRepository.findById(id);
			return usuarioBuscado.get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Usuario save(Usuario entity) throws Exception {
		try {
			return usuarioRepository.save(entity);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Usuario update(Long id, Usuario entity) throws Exception {
		if (usuarioRepository.existsById(id)) {
			try {
				Usuario usuario = usuarioRepository.findById(id).get();
				if (entity.getNombre() != null) {
					usuario.setNombre(entity.getNombre());
				}
				if (entity.getApellido() != null) {
					usuario.setApellido(entity.getApellido());
				}
				if (entity.getNro_celular() != null) {
					usuario.setNro_celular(entity.getNro_celular());
				}
				if (entity.getEmail() != null) {
					usuario.setEmail(entity.getEmail());
				}
				if (entity.getRoles() != null) {
					usuario.setRoles(entity.getRoles());
				}
				return usuarioRepository.save(usuario);

			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		} else {
			throw new ExpectableException("{\"error\":\"Error. No se encontró el elemento.\"}");
		}
	}

	@Override
	public boolean delete(Long id) throws Exception {
		if (usuarioRepository.existsById(id)) {
			try {
				usuarioRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		} else {
			throw new ExpectableException("{\"error\":\"Error. No se encontró el elemento.\"}");
		}
	}
}

