package com.ctt.addressservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableEurekaClient annotation is not required in latest spring versions. Simply adding spring-cloud-starter-netflix-eureka-client to dependencies will enable the client.
public class AddressServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressServiceApplication.class, args);
	}

}
