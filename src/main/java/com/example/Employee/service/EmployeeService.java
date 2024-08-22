package com.example.Employee.service;

import com.example.Employee.entity.Employee;
import com.example.Employee.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {

    public Employee createEmployee(Employee employee);

    public Employee getEmployeeById(Long id) throws ResourceNotFoundException;

    public List<Employee> getAllEmployees();

    public Employee updateEmployee(Long id, Employee employee);

    public void deleteEmployee(Long id);


}
