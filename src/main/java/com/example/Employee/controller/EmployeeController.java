package com.example.Employee.controller;


import com.example.Employee.entity.Employee;
import com.example.Employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
//@RequestMapping("/api/employees")
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;


    @PostMapping("/register")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        log.info("Received request to create employee: {}", employee);
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        log.info("Received request to fetch employee with id: {}", id);
        return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        log.info("Received request to fetch all employees");
        return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id,@RequestBody Employee employee) {
        log.info("Received request to update employee with id: {}", id);
        return new ResponseEntity<>(employeeService.updateEmployee(id,employee),HttpStatus.OK);
    }


}
