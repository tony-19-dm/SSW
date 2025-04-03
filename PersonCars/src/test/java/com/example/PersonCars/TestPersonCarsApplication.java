package com.example.PersonCars;

import org.springframework.boot.SpringApplication;

public class TestPersonCarsApplication {

	public static void main(String[] args) {
		SpringApplication.from(PersonCarsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
