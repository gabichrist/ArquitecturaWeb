package com.usuario.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.usuario.dtos.MonopatinDto;
import com.usuario.exception.ExpectableException;
import com.usuario.model.Cuenta;
import com.usuario.model.Usuario;
import com.usuario.repository.UsuarioRepository;

@Service("usuarioServicio")
public class UsuarioService implements BaseService<Usuario> {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ViajesMonopatinService viajesMonopatinService;

	@Override
	public List<Usuario> findAll() throws Exception {
		return usuarioRepository.findAll();
	}

	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findUserByEmailIgnoreCase(email);
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
			String encryptedPassword = passwordEncoder.encode(entity.getPassword());
			entity.setPassword(encryptedPassword);
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
				if (entity.getRol() != null) {
					usuario.setRol(entity.getRol());
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

	public Usuario agregarCuentaUsuario(Long id, Cuenta cuenta) throws Exception {
		if (usuarioRepository.existsById(id)) {
			try {
				Usuario usuario = usuarioRepository.findById(id).get();
				Set<Cuenta> usuariosCuenta = usuario.getCuentas();
				Set<Usuario> usuarios = new HashSet<Usuario>();
				usuarios.add(usuario);
				cuenta.setUsuarios(usuarios);
				usuariosCuenta.add(cuenta);
				usuarioRepository.save(usuario);
				return usuario;
			} catch (Exception e) {
				System.out.println("Error " + e);
				throw new Exception(e.getMessage());
			}
		}
		throw new ExpectableException("{\"error\":\"Error. No existe el usuario.\"}");

	}

	public List<MonopatinDto> getMonopatinesEnLaZona(Float latitud, Float longitud) {
		try {
			return this.viajesMonopatinService.getMonopatinesEnLaZona(latitud, longitud);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
