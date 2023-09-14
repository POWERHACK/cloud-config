package com.example.eurekaclientmicro2.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.eurekaclientmicro2.model.Employee;
import com.example.eurekaclientmicro2.responce.AddressResponce;
import com.example.eurekaclientmicro2.responce.EmployeeResponce;
import com.example.eurekaclientmicro2.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController

public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@GetMapping("/employee/{id}")
	public ResponseEntity<EmployeeResponce> getEmployeeDetails(@PathVariable("id") int id) {
		EmployeeResponce employee1 = employeeService.getEmployeeById(id);
		return new ResponseEntity(employee1, HttpStatus.FOUND);
	}

	
//	CompletableFuture is used for asynchronous programming in Java. Asynchronous programming is a
//	means of writing non-blocking code by running a task on a separate thread than the main application
//	thread and notifying the main thread about its progress, completion or failure.
//	This way, your main thread does not block/wait for the completion of the task and it can execute 
//	other tasks in parallel.
//	Having this kind of parallelism greatly improves the performance of your programs.
	
	@PostMapping("/employee")
    @TimeLimiter(name = "addEmployeeTL")
	public CompletableFuture<String> addEmployee(@RequestBody Employee employee) {
		String msg = employeeService.addEmployee(employee);
        return CompletableFuture.supplyAsync(()->getResponse(msg));
	}
	private String getResponse(String str) {
        if (Math.random() < 0.4) {       //Expected to fail 40% of the time
            return "Executing Within the time Limit... & "+str;
        } else {
            try {
                logger.info("Getting Delayed Execution");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Exception due to Request Timeout.";
     }

	
	@GetMapping("/getemployeeaddress/{id}")
	@Retry(name = "getEmployeeAddressRetry", fallbackMethod = "getEmployeeAddressFallback")
	public ResponseEntity<AddressResponce> getEmployeeAddress(@PathVariable("id") int id) {
		ResponseEntity<AddressResponce> emp = employeeService.getEmployeeAddress(id);
		return emp;
	}

	public ResponseEntity<String> getEmployeeAddressFallback(@PathVariable("id") int id, Exception e) {
		logger.info("---RESPONSE FROM FALLBACK METHOD---");
		return new ResponseEntity<>("SERVICE IS DOWN, PLEASE TRY AFTER SOMETIME !!!", HttpStatus.SERVICE_UNAVAILABLE);
	}

	
	@GetMapping("/getemployees")
	@RateLimiter(name = "getEmployeesRateLimit", fallbackMethod = "getEmployeesFallBack")
	public ResponseEntity<List<Employee>>  getEmployees() {
		List<Employee> employees = employeeService.showAllEmployee();
		return new ResponseEntity(employees, HttpStatus.FOUND);
	}

	public ResponseEntity<String> getEmployeesFallBack(RequestNotPermitted exception) {
		logger.info("Rate limit has applied, So no further calls are getting accepted");
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
				.body("Too many requests : No further request will be accepted. Please try after sometime");
	}

	@GetMapping("/getalladdresses")
    @CircuitBreaker(name = "getAllAddressCB", fallbackMethod = "getAllAddressFallback") 
	public ResponseEntity<List<AddressResponce>> getAllAddress(){	
		List<AddressResponce> allAddresses = employeeService.getAllAddressesFromAddressService();	
		return new ResponseEntity(allAddresses, HttpStatus.FOUND);
		
	}
	public ResponseEntity<String> getAllAddressFallback(Exception e) {
        logger.info("---RESPONSE FROM FALLBACK METHOD---");
        return new ResponseEntity("SERVICE IS DOWN, PLEASE TRY AFTER SOMETIME !!!", HttpStatus.SERVICE_UNAVAILABLE);
     }
	
	@DeleteMapping("/deletealldata")
	public String deleteAllData() {
		String msg = employeeService.delateAllData();
		return msg;
	}

}
