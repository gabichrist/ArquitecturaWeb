package com.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.usuario.model.Usuario;

public interface UsuarioRepository extends RepoBase<Usuario, Long>{
	static List<String> sorteableFields = List.of("id_usuario", "nombre","apellido","nro_celular","email","roles");
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	public Usuario findByEmail(String email);
	
	
	
}
