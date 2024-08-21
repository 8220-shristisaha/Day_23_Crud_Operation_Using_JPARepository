package com.example.Employee.repository;

import com.example.Employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Repository extends JpaRepository<Employee, Long> {
}
