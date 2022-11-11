package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import lombok.extern.slf4j.Slf4j;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = { 
		"controller", 
		"com.example.classes",
		"com.example.entities",
		"com.example.repositories",
		"com.example.services",
		"com.example.demo.configs",
		"com.example.advice",
		"com.example.dto","vehiclepackage"} )
@EnableJpaRepositories("com.example.repositories")
@EntityScan(basePackages = {"com.example.entities", "vehiclepackage"})
public class NewSpringProjectApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(NewSpringProjectApplication.class, args);

	}

}
