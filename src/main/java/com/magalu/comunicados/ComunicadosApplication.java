package com.magalu.comunicados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.magalu.comunicados.domain.entity")
@SpringBootApplication
public class ComunicadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComunicadosApplication.class, args);
	}

}
