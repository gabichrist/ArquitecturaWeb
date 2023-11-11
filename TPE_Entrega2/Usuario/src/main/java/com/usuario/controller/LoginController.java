package com.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.dtos.LoginDTO;
import com.usuario.exception.ExpectableException;
import com.usuario.service.LoginService;
import com.usuario.service.UsuarioService;
import com.usuario.utils.PasswordUtils;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginService loginService;

	@PostMapping("/")
	public ResponseEntity<?> login(@RequestBody LoginDTO login) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(loginService.login(login));
		} catch (ExpectableException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\":\"Error. Por favor intente más tarde.\"}");
		}
	}
}
