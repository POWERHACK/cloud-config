package com.example.eurekaclientmicro3.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eurekaclientmicro3.model.Address;
import com.example.eurekaclientmicro3.repo.AddressRepo;
import com.example.eurekaclientmicro3.responce.AddressResponse;

@Service
public class AddressService {
  
    @Autowired
    private AddressRepo addressRepo;
  
    @Autowired
    private ModelMapper mapper;
    
    
 
    	
  
    public AddressResponse findAddressByEmployeeId(int employee_id) {
    	List<Address> addressList = addressRepo.findAll();
    	Address addressByEmployeeId;
    	for(Address adr : addressList) {
    		if(adr.getEmployee_id()== employee_id) {
    			 addressByEmployeeId = adr;
    			 AddressResponse addressResponse = mapper.map(addressByEmployeeId, AddressResponse.class);
    		        return addressResponse;
    		}
    	}
    	return null;
    }
    
    
    public String addAddress(Address address) {
    	addressRepo.save(address);
    	int id = address.getId();
    	return "Address Added successfully and id is:- " + id; 
    	
    	
    }


	public List<Address> getAllAddresses() {
		List<Address> addresses = addressRepo.findAll();	
		return addresses;
	}


	public AddressResponse getAddressById(int id) {
		
		AddressResponse addressResponse = mapper.map(addressRepo.findById(id).get(), AddressResponse.class);
		return addressResponse;
	}
}