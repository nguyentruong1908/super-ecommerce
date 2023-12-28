package com.example.webshopserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WebShopServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebShopServerApplication.class, args);
	}

}
