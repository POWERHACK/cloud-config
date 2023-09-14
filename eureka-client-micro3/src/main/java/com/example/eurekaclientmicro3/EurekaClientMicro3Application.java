package com.example.eurekaclientmicro3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication

public class EurekaClientMicro3Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientMicro3Application.class, args);
	}

}
