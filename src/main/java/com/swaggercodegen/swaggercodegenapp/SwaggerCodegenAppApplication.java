package com.swaggercodegen.swaggercodegenapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.swaggercodegen.swaggercodegenapp.model.TransactionDetail;
import com.swaggercodegen.swaggercodegenapp.services.TransfromData;

@SpringBootApplication
public class SwaggerCodegenAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerCodegenAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			TransfromData service) {
		return args -> {
			TransactionDetail transform = service.transform();
		};
	}

}
