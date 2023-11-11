package com.usuario.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.usuario.dtos.LoginDTO;
import com.usuario.exception.ExpectableException;
import com.usuario.model.Usuario;
import com.usuario.utils.PasswordUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SuppressWarnings("deprecation")
@Service()
public class LoginService {
	
	@Autowired
	private UsuarioService userService;
	
	private String secretKey = "QJeKx+s7XIv1WbBlj7vJ9CD3Ozj1rB3qjlNZY9ofWKJSaBNBo5r1q9Rru/OWlYb+UHV1n4/LJl1OBYYZZ7rhJEnn5peyHCd+eLJfRdArE37pc+QDIsJlabQtR7tYRa+SnvGRyL01uZsK33+gezV+/GPXBnPTj8fOojDUzJiPAvE=";;
	
	private String getJWTToken(Usuario u) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("email", u.getEmail());
		claims.put("nombre", u.getNombre());
		claims.put("apellido", u.getApellido());
		claims.put("nro_celular", u.getNro_celular());
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(u.getRol().name());
		String token = Jwts.builder().setId("unused")
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.addClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		return token;
	}
	
	public String login(LoginDTO loginInfo) throws ExpectableException {
		Usuario u = this.userService.findByEmail(loginInfo.getEmail());
		if (u == null || !PasswordUtils.authenticate(loginInfo.getPassword(), u.getPassword())) {
			throw new ExpectableException("{\"error\":\"Error contraseña invalida\"}");
		}
		return this.getJWTToken(u);
	}
	
}