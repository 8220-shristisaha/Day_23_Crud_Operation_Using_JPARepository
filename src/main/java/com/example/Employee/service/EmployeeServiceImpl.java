package com.example.Employee.service;

import com.example.Employee.entity.Employee;
import com.example.Employee.exception.ResourceNotFoundException;
import com.example.Employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    @Transactional
    public Employee createEmployee(Employee employee) {
        log.info("Creating employee: {}", employee);
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee getEmployeeById(Long id) throws ResourceNotFoundException {

        log.info("Fetching employee with id: {}", id);
        Optional<Employee> byId = employeeRepository.findById(id);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("ID doesn't Exists");
        } else {
            return byId.get();
        }
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee updateEmployee(Long id, Employee employee) {
        log.info("Updating employee with id: {}", id);
        Employee byId = employeeRepository.findById(id).get();

        if (Objects.nonNull(byId.getFirstName()) &&
                !"".equalsIgnoreCase(byId.getFirstName())) {
            byId.setFirstName(employee.getFirstName());
        }

        if (Objects.nonNull(byId.getLastName()) &&
                !"".equalsIgnoreCase(byId.getLastName())) {
            byId.setLastName(employee.getLastName());
        }

        if (Objects.nonNull(byId.getEmail()) &&
                !"".equalsIgnoreCase(byId.getEmail())) {
            byId.setEmail(employee.getEmail());
        }

        if (Objects.nonNull(byId.getDepartment()) &&
                !"".equalsIgnoreCase(byId.getDepartment())) {
            byId.setDepartment(employee.getDepartment());
        }

        if (Objects.nonNull(byId.getSalary())) {
            byId.setSalary(employee.getSalary());
        }

        return employeeRepository.save(byId);
    }

    @Override
    @Transactional

    public void deleteEmployee(Long id) {
        log.info("Deleting employee with id: {}", id);
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void uploadEmployeeData(MultipartFile file) throws Exception {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Employee employee = new Employee();
                employee.setFirstName(data[0]);
                employee.setLastName(data[1]);
                employee.setEmail(data[2]);
                employee.setDepartment(data[3]);
                employee.setSalary(Double.parseDouble(data[4]));

                employees.add(employee);

            }
        } catch (IOException e) {
            log.error("Error parsing CSV file: {}", e.getMessage());
            throw new Exception("Failed to parse CSV file");
        }

        for (Employee e : employees) {
            try {
                employeeRepository.save(e);
                log.info("Successfully saved employee: {}", e);
            } catch (Exception ex) {
                log.error("Failed to save employee: {}", e);
            }
        }

    }
}
