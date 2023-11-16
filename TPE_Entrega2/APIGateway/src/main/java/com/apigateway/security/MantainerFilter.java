package com.apigateway.security;

import com.apigateway.dto.ValidateTokenDTO;
import com.apigateway.enums.Roles;

import lombok.NoArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

/**
 * Authentication pre-filter for API gateway.
 */
@Component
public class MantainerFilter extends AbstractGatewayFilterFactory<MantainerFilter.Config> {

	private static final String _AuthHeader = "Authorization";
	List<String> excludedUrls = List.of("auth/login", "auth/register");
	private final WebClient.Builder webClientBuilder;

	public MantainerFilter(WebClient.Builder webClientBuilder) {
		super(Config.class);
		this.webClientBuilder = webClientBuilder;
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			String bearerToken = request.getHeaders().getFirst(_AuthHeader);
			String tokenValue = this.getToken(bearerToken);
			if (this.isSecured.test(request)) {
				return webClientBuilder.build().post().uri("http://localhost:8080/auth/validate").bodyValue(tokenValue)
						.header(_AuthHeader, bearerToken).retrieve().bodyToMono(ValidateTokenDTO.class)
						.map(response -> {
							if (!response.isAuthenticated() || response.getRol() != Roles.ROLE_MANTAINER)
								throw new BadCredentialsException("JWT invalido");
							return exchange;
						}).flatMap(chain::filter);
			}
			return chain.filter(exchange);
		};
	}

	private final Predicate<ServerHttpRequest> isSecured = request -> excludedUrls.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));

	private String getToken(String bearerToken) {
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	/**
	 * Required by AbstractGatewayFilterFactory
	 */
	@NoArgsConstructor
	public static class Config {
	}

}
