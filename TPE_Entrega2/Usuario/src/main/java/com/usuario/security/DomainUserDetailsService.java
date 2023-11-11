package com.usuario.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.usuario.model.Usuario;
import com.usuario.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

	private final UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findUserByEmailIgnoreCase(email).map(this::createSpringSecurityUser)
				.orElseThrow(() -> new UsernameNotFoundException("No existe el usuario con email " + email));
	}

	private UserDetails createSpringSecurityUser(Usuario user) {
		String userRole = user.getRol().name();

		return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
				.password(user.getPassword()).roles(userRole).build();
	}

}
