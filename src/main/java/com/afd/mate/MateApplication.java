package com.afd.mate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;

@SpringBootApplication(exclude = {ReactiveSecurityAutoConfiguration.class})
public class MateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MateApplication.class, args);
	}

}
