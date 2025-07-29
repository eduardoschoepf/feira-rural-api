package com.feirarural.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.feirarural.api.config.security.JwtProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class FeiraRuralApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeiraRuralApiApplication.class, args);
	}

}
