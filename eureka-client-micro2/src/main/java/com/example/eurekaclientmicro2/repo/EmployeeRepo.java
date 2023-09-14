package com.example.eurekaclientmicro2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eurekaclientmicro2.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
}