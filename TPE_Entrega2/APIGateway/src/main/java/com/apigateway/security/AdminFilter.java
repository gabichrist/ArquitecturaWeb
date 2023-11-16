package com.apigateway.security;

import com.apigateway.dto.ValidateTokenDTO;
import com.apigateway.enums.Roles;

import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;

/**
 * Authentication pre-filter for API gateway.
 */
@Component
public class AdminFilter extends AbstractGatewayFilterFactory<AdminFilter.Config> {

	private static final String _AuthHeader = "Authorization";
	List<String> excludedUrls = List.of("auth/login", "auth/register");
	private final WebClient.Builder webClientBuilder;

	public AdminFilter(WebClient.Builder webClientBuilder) {
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
				try {
					return webClientBuilder.build().post().uri("http://localhost:8080/auth/validate")
							.bodyValue(tokenValue).header(_AuthHeader, bearerToken).retrieve()
							.bodyToMono(ValidateTokenDTO.class).flatMap(response -> {
								if (!response.isAuthenticated() || response.getRol() != Roles.ROLE_ADMIN) {
									throw new BadCredentialsException("Unauthorized user");
								}
								return chain.filter(exchange);
							});
				} catch (Exception e) {
					throw new BadCredentialsException("Unauthorized user");
				}
			}

			return chain.filter(exchange);
		};

	}

	private Mono<Void> returnError(ServerWebExchange exchange, String errorMessage) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(HttpStatus.UNAUTHORIZED);

		byte[] messageBytes = errorMessage.getBytes();
		DataBuffer buffer = response.bufferFactory().wrap(messageBytes);

		return response.writeWith(Mono.just(buffer)).then(response.setComplete());
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
