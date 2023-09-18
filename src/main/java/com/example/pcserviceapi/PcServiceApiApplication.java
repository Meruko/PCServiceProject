package com.example.pcserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PcServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcServiceApiApplication.class, args);
	}

}
