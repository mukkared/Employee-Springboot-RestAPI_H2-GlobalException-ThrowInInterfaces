package com.navin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.navin.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}