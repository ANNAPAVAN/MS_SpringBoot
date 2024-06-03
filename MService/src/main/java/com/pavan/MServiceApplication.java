package com.pavan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MServiceApplication.class, args);
//		https://www.youtube.com/watch?v=mPPhcU7oWDU&t=871s
		System.out.println("Micro Service Project Started.........");
	}

}
