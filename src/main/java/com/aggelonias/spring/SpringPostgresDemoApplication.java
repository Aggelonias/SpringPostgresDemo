package com.aggelonias.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringPostgresDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPostgresDemoApplication.class, args);
	}

}
