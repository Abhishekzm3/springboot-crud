package com.example.crudOperations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);

		// Learning Git with SpringBoot
		// This change is only on feature/test
		// 3rd line
		// this 4th line is for PR test purpose
		System.out.println("This code has a bug");
	}

}

