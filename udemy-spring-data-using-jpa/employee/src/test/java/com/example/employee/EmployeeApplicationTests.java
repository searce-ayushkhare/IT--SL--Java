package com.example.employee;

import com.example.employee.entities.Employee;
import com.example.employee.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeApplicationTests {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testCreate() {
		Employee employee = new Employee();
		employee.setName("John");

		employeeRepository.save(employee);
	}

}
