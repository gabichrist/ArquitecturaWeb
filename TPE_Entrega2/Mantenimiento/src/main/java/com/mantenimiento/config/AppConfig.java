package com.mantenimiento.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class AppConfig {

   
    @Bean("OpenAPI")
    public OpenAPI customOpenAPI(@Value("${app-description}") String description,
                                 @Value("${app-version}") String version) {
        return new OpenAPI()
                .info(new Info().title("Mantenimiento API")
                        .version(version)
                        .description(description));
    }
}