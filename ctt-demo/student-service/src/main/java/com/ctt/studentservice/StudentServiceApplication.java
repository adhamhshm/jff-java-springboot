package com.ctt.studentservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients // this is added to use feign client, need to add dependency first
// @EnableEurekaClient annotation is not required in latest spring versions. Simply adding spring-cloud-starter-netflix-eureka-client to dependencies will enable the client.
public class StudentServiceApplication {

	// to be used with the web client
	@Value("${address.service.url}")
	private String addressServiceUrl;

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

	// will be use throughout our student service
	@Bean
	public WebClient webClient () {

		WebClient webClient = WebClient.builder()
			.baseUrl(addressServiceUrl).build();

			return webClient;
	}

}
