package com.gateway.service.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

//
//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		// adding 2 rotes to first microservice as we need to log request body if method is POST
//		return builder.routes()
//				.route("user", r -> r.path("/user/**")
//						.uri("http://localhost:8081"))
//				.build();
//	}

}
