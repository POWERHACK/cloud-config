package com.example.eurekaclientmicro3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.eurekaclientmicro3.model.Address;
import com.example.eurekaclientmicro3.responce.AddressResponse;
import com.example.eurekaclientmicro3.service.AddressService;

@RestController
public class AddressController {
  
    @Autowired
    private AddressService addressService;
  
    @GetMapping("/address/{employee_id}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employee_id") int employee_id) {
        AddressResponse addressResponse = addressService.findAddressByEmployeeId(employee_id);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
    }
    
    
    @PostMapping("/addaddress")
    public String addAddress(@RequestBody Address address) {
    	String msg = addressService.addAddress(address);
    	return msg;
    }
    
    @GetMapping("/getalladdresses")
    public List<Address> getAllAdresses(){
    	
    	List<Address> allAddresses = addressService.getAllAddresses();
    	
    	return allAddresses;

    			
    	
    }
  
}