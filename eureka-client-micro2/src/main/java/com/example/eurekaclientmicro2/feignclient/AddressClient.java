package com.example.eurekaclientmicro2.feignclient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.eurekaclientmicro2.responce.AddressResponce;
@FeignClient(name = "address-service", path = "/address-service")
@RibbonClient(name = "address-service")
public interface AddressClient {

	@GetMapping("/address/{id}")
    public ResponseEntity<AddressResponce> getAddressByEmployeeId(@PathVariable("id") int id);
	
}
