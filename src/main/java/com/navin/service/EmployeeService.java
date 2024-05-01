package com.navin.service;

import java.util.List;
import java.util.Map;

import com.navin.entity.Employee;
import com.navin.exception.ResourceNotFoundException;

public interface EmployeeService {

	public List<Employee> getAllEmployees() throws ResourceNotFoundException;

	public Employee getEmployeeById(Long employeeId);// throws ResourceNotFoundException;

	public Employee createEmployee(Employee employee) throws ResourceNotFoundException;

	public Employee updateEmployee(long employeeId, Employee employeeDetails) throws ResourceNotFoundException;

	public Map<String, Boolean> deleteEmployee(Long employeeId) throws ResourceNotFoundException;

}
