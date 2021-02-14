package com.sudip.microserviceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroservicesDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesDiscoveryServerApplication.class, args);
	}

}
