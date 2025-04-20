package com.example.CustomerWitchAutorisation;

import org.springframework.boot.SpringApplication;

public class TestCustomerWitchAutorisationApplication {

	public static void main(String[] args) {
		SpringApplication.from(CustomerWitchAutorisationApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
