package com.example.Employee.service;

import com.example.Employee.entity.Employee;
import com.example.Employee.exception.ResourceNotFoundException;
import com.example.Employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    @Transactional
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee getEmployeeById(Long id) throws ResourceNotFoundException {

        Optional<Employee> byId = employeeRepository.findById(id);
        if(!byId.isPresent()){
            throw new ResourceNotFoundException("ID doesn't Exists");
        }
        else{
            return byId.get();
        }
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee updateEmployee(Long id, Employee employee) {
        Employee byId = employeeRepository.findById(id).get();
        if(byId == null){
            return null;
        }

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
    @Transactional

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
