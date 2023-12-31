package com.usuario.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Crea el token
 */
@Component
public class TokenProvider {

	private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

	private static final String AUTHORITIES_KEY = "auth";

	private static final String INVALID_JWT_TOKEN = "Invalid JWT token.";
	private final String secret = "QJeKx+s7XIv1WbBlj7vJ9CD3Ozj1rB3qjlNZY9ofWKJSaBNBo5r1q9Rru/OWlYb+UHV1n4/LJl1OBYYZZ7rhJEnn5peyHCd+eLJfRdArE37pc+QDIsJlabQtR7tYRa+SnvGRyL01uZsK33+gezV+/GPXBnPTj8fOojDUzJiPAvE=";

	private final Key key;

	private final JwtParser jwtParser;

	private final long tokenValidityInMilliseconds = 28800 * 1000; // valido por 8hs.

	public TokenProvider() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		key = Keys.hmacShaKeyFor(keyBytes);
		jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
	}

	/**
	 * Crea el token.
	 * 
	 * @param authentication
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String createToken(Authentication authentication) {
		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		long now = (new Date()).getTime();
		Date validity = new Date(now + this.tokenValidityInMilliseconds);
		return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities)
				.setExpiration(validity).signWith(SignatureAlgorithm.HS512, secret) 
                .compact();
	}

	public Authentication getAuthentication(String token) {
		Claims claims = jwtParser.parseClaimsJws(token).getBody();

		Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).filter(auth -> !auth.trim().isEmpty())
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());

		User principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}

	public String getUsernameFromToken(String token) {
		return getClaims(token, Claims::getSubject);
	}

	public <T> T getClaims(String token, Function<Claims, T> type) {
		final Claims claims = getAllClaimsFromToken(token);
		return type.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getKey() {
		byte[] decodedKey = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(decodedKey);
	}

	/**
	 * Valida el token.
	 * 
	 * @param authToken
	 * @return
	 */
	public boolean validateToken(String authToken) {
		try {
			jwtParser.parseClaimsJws(authToken);
			return true;
		} catch (ExpiredJwtException e) {
			throw new ExpiredJwtException(e.getHeader(), e.getClaims(), e.getMessage());
		} catch (UnsupportedJwtException | MalformedJwtException | SecurityException e) {
			log.trace(INVALID_JWT_TOKEN, e);
		} catch (IllegalArgumentException e) {
			log.error("Token validation error {}", e.getMessage());
		}
		return false;
	}
}