package com.example.crudsample.controllers;

import com.example.crudsample.customexceptions.ControllerException;
import com.example.crudsample.customexceptions.ServiceException;
import com.example.crudsample.entities.Employee;
import com.example.crudsample.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/save")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        try {
            return new ResponseEntity<>(employeeServiceImpl.addEmployee(employee), HttpStatus.CREATED);
        } catch (ServiceException e) {
            return new ResponseEntity<>(new ControllerException(e.getErrorCode(), e.getErrorMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ControllerException("610", "Something went wrong in controller while saving an employee :: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<?> getEmployee(@PathVariable("empId") Long empIdL) {
        try{
            return new ResponseEntity<>(employeeServiceImpl.getEmployeeById(empIdL), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(new ControllerException(e.getErrorCode(), e.getErrorMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ControllerException("611", "Something went wrong in controller fetching the employee :: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeServiceImpl.getAllEmployees(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("empId") Long empIdL) {
        employeeServiceImpl.deleteEmployeeById(empIdL);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeServiceImpl.updateEmployee(employee), HttpStatus.CREATED);
    }
}
