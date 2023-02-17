package com.example.crudsample.services;

import com.example.crudsample.customexceptions.ServiceException;
import com.example.crudsample.entities.Employee;
import com.example.crudsample.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        if (employee.getName().isEmpty() || employee.getName().length() == 0) {
            throw new ServiceException("601","Please send proper name, it is blank");
        }

        try {
            return employeeRepository.save(employee);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("602", "Employee given is null :: " + e.getMessage());
        } catch (Exception e) {
            throw new ServiceException("603", "Something went wrong in Service layer while adding the employee :: " + e.getMessage());
        }
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        try {
            return employeeRepository.findById(empId).get();
        } catch (IllegalArgumentException e) {
            throw new ServiceException("606", "Employee ID is null, please provide some ID to fetch the employee :: " + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new ServiceException("607", "Given employee Id does not exist in the database :: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList;
        try {
            employeeList = employeeRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("605", "Something went wrong in Service layer while fetching employee list :: " + e.getMessage());
        }

        if (employeeList.isEmpty()) {
            throw new ServiceException("604", "Employee list is empty, please add some first!");
        }

        return employeeList;
    }

    @Override
    public void deleteEmployeeById(Long empId) {
        try{
            employeeRepository.deleteById(empId);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("608", "Employee ID is null, please provide some ID to delete the employee :: " + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new ServiceException("609", "Given employee Id already does not exist in the database :: " + e.getMessage());
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if (employee.getName().isEmpty() || employee.getName().length() == 0) {
            throw new ServiceException("601","Please send proper name, it is blank");
        }

        try{
            return employeeRepository.save(employee);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("602", "Employee given is null :: " + e.getMessage());
        } catch (Exception e) {
            throw new ServiceException("603", "Something went wrong in Service layer while updating the employee :: " + e.getMessage());
        }
    }
}
