package com.usuario.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.usuario.dtos.LoginDTO;
import com.usuario.model.Usuario;
import com.usuario.security.TokenProvider;
import com.usuario.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final UserDetailsService userDetailsService;
	
	@Autowired
	private UsuarioService usuarioService;


	@PostMapping("/login")
	public ResponseEntity<JWTToken> authenticate(@Valid @RequestBody LoginDTO request) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				request.getEmail(), request.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final var jwt = tokenProvider.createToken(authentication);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);

	}

	@PostMapping({ "", "/register" })
	public ResponseEntity<?> save(@RequestBody Usuario usuario) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.save(usuario));
		} catch (Exception e2) {
			System.out.println("error " + e2.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}

	/**
	 * Valida el token y devuelve un JSON con nombre de usuario y sus autoridades.
	 */
	@PostMapping(value = "/validate")
	public ResponseEntity<?> validar(@RequestBody String token) {
		String roleResponse = null;

		try {
			String username = this.tokenProvider.getUsernameFromToken(token);
			
			if (username != null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				if (this.tokenProvider.validateToken(token)) {
					roleResponse = userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(", "));
				}
				if (roleResponse == null) {
					return new ResponseEntity<>("Unauthorized user", HttpStatus.UNAUTHORIZED);
				} else {
					ValidateTokenDTO response = new ValidateTokenDTO(true, username, roleResponse);
					HttpHeaders headers = new HttpHeaders();
					return new ResponseEntity<>(response, headers, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid token", HttpStatus.FORBIDDEN);
		}
		return null;
	}

	@Data
	@Builder
	public static class ValidateTokenDTO {
		private boolean isAuthenticated;
		private String username;
		private String rol;
	}

	static class JWTToken {
		private String idToken;

		JWTToken(String idToken) {
			this.idToken = idToken;
		}

		@JsonProperty("id_token")
		String getIdToken() {
			return idToken;
		}

		void setIdToken(String idToken) {
			this.idToken = idToken;
		}
	}

}