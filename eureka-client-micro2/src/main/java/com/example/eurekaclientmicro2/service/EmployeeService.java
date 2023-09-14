package com.example.eurekaclientmicro2.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.eurekaclientmicro2.feignclient.AddressClient;
import com.example.eurekaclientmicro2.model.Employee;
import com.example.eurekaclientmicro2.repo.EmployeeRepo;
import com.example.eurekaclientmicro2.responce.AddressResponce;
import com.example.eurekaclientmicro2.responce.EmployeeResponce;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper mapper;
   
    @Autowired
    RestTemplate restTemplate;
  
    @Autowired
    private AddressClient addressClient;
    
    public EmployeeResponce getEmployeeById(int id) {
        Employee employee = employeeRepo.findById(id).get();
        EmployeeResponce employeeResponse = mapper.map(employee, EmployeeResponce.class);
  
        // Using FeignClient
        ResponseEntity<AddressResponce> addressResponse = addressClient.getAddressByEmployeeId(id);
        employeeResponse.setAddressResponse(addressResponse.getBody());
  
        return employeeResponse;       
    }  
    
    
    public String addEmployee(Employee employee){
        employeeRepo.save(employee);
        return "Employee added successfully";
    }

    public Employee getById(int empId){
        Employee emp = (Employee) employeeRepo.findById(empId).get();
        return emp;
    }

    public List<Employee> showAllEmployee(){
        List<Employee> employees = employeeRepo.findAll();
        return employees;
    }
    
    public String delateAllData()
    {
    	employeeRepo.deleteAll();
    	
    	return "All record deleted successfully";
    }


	public ResponseEntity<AddressResponce> getEmployeeAddress(int id) {
		
        ResponseEntity<AddressResponce> addressResponse = addressClient.getAddressByEmployeeId(id);

		return addressResponse;
	}


	public List<AddressResponce> getAllAddressesFromAddressService() {
//		List<AddressResponce> allAddresses = (List<AddressResponce>) mapper.map(addressClient.getAllAddresses(), AddressResponce.class);
		ResponseEntity<AddressResponce[]> allAddresses = restTemplate.getForEntity("http://localhost:9001/address-service/getalladdresses", AddressResponce[].class);	
		AddressResponce[] addresses = allAddresses.getBody();
		return Arrays.asList(addresses);
	}
    
    
}
