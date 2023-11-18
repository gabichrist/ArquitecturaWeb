package com.apigateway.router;

import com.apigateway.security.AdminFilter;
import com.apigateway.security.AuthenticationFilter;
import com.apigateway.security.MantainerFilter;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder, AuthenticationFilter authFilter, MantainerFilter mantFilter,
			AdminFilter admFilter) {
		return builder.routes().route("auth-1",
				r -> r.path("/auth/login").filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
						.uri("http://localhost:8080"))
				.route("auth-2",
						r -> r.path("/auth/register")
								.filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
								.uri("http://localhost:8080"))
				.route("micro-usuarios-1",
						r -> r.path("/usuarios/**").filters(f -> f.filter(admFilter.apply(new AdminFilter.Config())))
								.uri("http://localhost:8080"))
				.route("auth-anular-cuenta", r -> r.path("/cuentas/*/anular")
						.filters(f -> f.filter(admFilter.apply(new AdminFilter.Config()))).uri("http://localhost:8080"))
				.route("auth-cargar-saldo",
						r -> r.path("/cuentas/*/cargar-saldo")
								.filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
								.uri("http://localhost:8080"))
				.route("auth-cargar-saldo",
						r -> r.path("/cuentas/*/descontar-saldo")
								.filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
								.uri("http://localhost:8080"))
				.route("auth-nueva-cuenta",
						r -> r.path("POST", "/cuentas")
								.filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
								.uri("http://localhost:8080"))
				.route("auth-eliminar-cuenta", r -> r.path("DELETE", "/cuentas/*")
						.filters(f -> f.filter(admFilter.apply(new AdminFilter.Config()))).uri("http://localhost:8080"))
				.route("micro-admin",
						r -> r.path("/admin/**").filters(f -> f.filter(admFilter.apply(new AdminFilter.Config())))
								.uri("http://localhost:8083"))
				.route("micro-mantainer",
						r -> r.path("/mantenimientos/**")
								.filters(f -> f.filter(mantFilter.apply(new MantainerFilter.Config())))
										
								.uri("http://localhost:8082"))

				.route("micro-monopatin",
						r -> r.path("GET", "/paradas/**")
								.filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
								.uri("http://localhost:8081"))
				.route("micro-monopatin", r -> r.path("POST", "/paradas**")
						.filters(f -> f.filter(admFilter.apply(new AdminFilter.Config()))).uri("http://localhost:8081"))
				.route("micro-monopatin", r -> r.path("DELETE", "/paradas**")
						.filters(f -> f.filter(admFilter.apply(new AdminFilter.Config()))).uri("http://localhost:8081"))
				.route("micro-monopatin", r -> r.path("PUT", "/paradas**")
						.filters(f -> f.filter(admFilter.apply(new AdminFilter.Config()))).uri("http://localhost:8081"))
				.route("micro-monopatin",
						r -> r.path("/tarifas/**").filters(f -> f.filter(admFilter.apply(new AdminFilter.Config())))
								.uri("http://localhost:8081"))
				.route("micro-monopatin",
						r -> r.path("/monopatines/disponibles/latitud/**")
								.filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
								.uri("http://localhost:8081"))
				.route("micro-monopatin",
						r -> r.path("/monopatines/disponibles**")
								.filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
								.uri("http://localhost:8081"))
				.route("micro-monopatin", r -> r.path("POST", "/monopatines**")
						.filters(f -> f.filter(admFilter.apply(new AdminFilter.Config()))).uri("http://localhost:8081"))
				.route("micro-monopatin", r -> r.path("PUT", "/monopatines**")
						.filters(f -> f.filter(admFilter.apply(new AdminFilter.Config()))).uri("http://localhost:8081"))
				.route("micro-monopatin", r -> r.path("DELETE", "/monopatines**")
						.filters(f -> f.filter(admFilter.apply(new AdminFilter.Config()))).uri("http://localhost:8081"))
				.route("micro-monopatin",
						r -> r.path("/viajes/**")
								.filters(f -> f.filter(authFilter.apply(new AuthenticationFilter.Config())))
								.uri("http://localhost:8081"))
				.build();
	}

}
