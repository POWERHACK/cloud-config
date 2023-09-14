package com.example.eurekaclientmicro3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eurekaclientmicro3.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {
  
//	public Address findAddressByEmployeeId(int employee_id);
}