package com.example.eurekaclientmicro2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class MIcro2Controller {

	@GetMapping("/hello-worlds")
	public String getHelloWorld ()
	{
	return "Well come to Micro2";
	}
	
	 @Value("${my.app.title}")
	    private String title;

	    @GetMapping("/data")
	    public ResponseEntity<String> showProductMsg() {
	        return new ResponseEntity<String>("Value of title from Config Server: "+title, HttpStatus.OK);
	    }

}
