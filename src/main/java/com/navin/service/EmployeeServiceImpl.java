package com.navin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navin.entity.Employee;
import com.navin.exception.ResourceNotFoundException;
import com.navin.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() throws ResourceNotFoundException {
		List<Employee> all = employeeRepository.findAll();
		if(all.isEmpty()) {
			throw new ResourceNotFoundException("Employee details does not exist: "); 
		}else {
			return all;
		}
	}

	@Override
	public Employee getEmployeeById(Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not found for this id :: " + employeeId));
		return employee;
	}

	@Override
	public Employee createEmployee(Employee employee) throws ResourceNotFoundException {
		//return employeeRepository.save(employee);
		Employee save = null;
		Employee id = employeeRepository.findById(employee.getId()).orElse(null);
		if(id == null) {
			save = employeeRepository.save(employee);
		}else {
			throw new ResourceNotFoundException("Employee already existed: "+employee.getId());
		}
		return save;
	}

	@Override
	public Employee updateEmployee(long employeeId, Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not found for this id " + employeeId));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		Employee updatedemployee = employeeRepository.save(employee);
		return updatedemployee;
	}

	@Override
	public Map<String, Boolean> deleteEmployee(Long employeeId) throws ResourceNotFoundException {
		employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not found for this id " + employeeId));
		employeeRepository.deleteById(employeeId);
		Map<String, Boolean> map = new HashMap<>();
		map.put("Deleted", true);
		return map;
	}

}