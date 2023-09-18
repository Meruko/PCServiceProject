package com.example.pcservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PcServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcServiceApplication.class, args);
	}

}
