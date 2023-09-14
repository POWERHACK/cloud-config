package com.example.eurekaclientmicro2.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EmpConfig {
	

    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }
    
    @Bean
    public RestTemplate restTemplate() { 	
    	return new RestTemplate();
    	
    }

}
