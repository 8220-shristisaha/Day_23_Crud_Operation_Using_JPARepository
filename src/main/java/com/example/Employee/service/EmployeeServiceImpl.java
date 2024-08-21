package com.example.Employee.service;

import com.example.Employee.entity.Employee;
import com.example.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee byId = employeeRepository.findById(id).get();

        if(Objects.nonNull(byId.getFirstName()) &&
            !"".equalsIgnoreCase(byId.getFirstName())){
            byId.setFirstName(employee.getFirstName());
        }

        if(Objects.nonNull(byId.getLastName()) &&
            !"".equalsIgnoreCase(byId.getLastName())){
            byId.setLastName(employee.getLastName());
        }

        if(Objects.nonNull(byId.getEmail()) &&
            !"".equalsIgnoreCase(byId.getEmail())){
            byId.setEmail(employee.getEmail());
        }

        if(Objects.nonNull(byId.getDepartment()) &&
            !"".equalsIgnoreCase(byId.getDepartment())){
            byId.setDepartment(employee.getDepartment());
        }

        if(Objects.nonNull(byId.getSalary())){
            byId.setSalary(employee.getSalary());
        }

        return employeeRepository.save(byId);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
