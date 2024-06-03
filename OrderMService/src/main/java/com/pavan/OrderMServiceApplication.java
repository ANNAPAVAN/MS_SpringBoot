package com.pavan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderMServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderMServiceApplication.class, args);
		System.out.println("Order Micro Service Started...........");
	}

}
