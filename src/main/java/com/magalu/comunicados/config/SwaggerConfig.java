package com.magalu.comunicados.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info()
				.title("API de Comunicados")
				.description("API para gerenciamento de comunicados internos")
				.version("1.0.0");

		return new OpenAPI()
				.info(info);
	}

}
