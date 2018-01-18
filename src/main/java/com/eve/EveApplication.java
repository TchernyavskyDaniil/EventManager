package com.eve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.eve.entity")
@SpringBootApplication
public class EveApplication  {
	public static void main(String[] args) {
		SpringApplication.run(EveApplication.class, args);
	}
}
