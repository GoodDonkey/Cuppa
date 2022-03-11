package com.cuppa.cuppa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CuppaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuppaApplication.class, args);
	}

}
