package com.myportfolio.mutualfundsms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MutualFundServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutualFundServiceApplication.class, args);
	}

}
