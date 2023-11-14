package com.usuario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usuario.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	static List<String> sorteableFields = List.of("id_usuario", "nombre", "apellido", "nro_celular", "email", "roles");

	Optional<Usuario> findUserByEmailIgnoreCase(String email);

}
