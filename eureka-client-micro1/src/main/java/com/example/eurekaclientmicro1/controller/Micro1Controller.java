package com.example.eurekaclientmicro1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Micro1Controller {
	@GetMapping("/hello-worlds")
	public String getHelloWorld ()
	{
	return "Well come to Micro1";
	}

}
