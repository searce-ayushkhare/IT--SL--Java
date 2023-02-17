package com.example.componentmapping;

import com.example.componentmapping.entities.Address;
import com.example.componentmapping.entities.Employee;
import com.example.componentmapping.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ComponentmappingApplicationTests {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreate() {
		Employee employee = new Employee();
		employee.setId(123);
		employee.setName("Ram");

		Address address = new Address();
		address.setStreetAddress("By-pass road");
		address.setCity("Jabalpur");
		address.setState("MP");
		address.setZipcode("444590");
		address.setCountry("India");

		employee.setAddress(address);

		employeeRepository.save(employee);
	}

}
