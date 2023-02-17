package com.example.crudsample.services;

import com.example.crudsample.entities.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    Employee getEmployeeById(Long empId);

    List<Employee> getAllEmployees();

    void deleteEmployeeById(Long empId);

    Employee updateEmployee(Employee employee);

}
