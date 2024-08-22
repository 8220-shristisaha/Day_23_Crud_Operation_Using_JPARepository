package com.example.Employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_table")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name cannot be Blank")
    private String firstName;

    @NotBlank(message = "Last Name cannot be Blank")
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "department cannot be empty")
    private String department;

    @Min(value = 10000,message = "Salary should not be less than 10k")
    @Max(value = 100000,message = "Salary should not be more than 100k ")
    private double salary;
}
