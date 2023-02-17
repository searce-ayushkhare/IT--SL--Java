package com.example.customer;

import com.example.customer.entities.Address;
import com.example.customer.entities.Customer;
import com.example.customer.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CustomerApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreate() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("raju");
		customer.setEmail("raju@gmail.com");

		Address address = new Address();
		address.setStreetaddress("bus stand road");
		address.setCity("Gwalior");
		address.setState("MP");
		address.setZipcode("412301");
		address.setCountry("India");

		customer.setAddress(address);

		customerRepository.save(customer);
	}

	@Test
	public void testRead() {
		Customer customer = customerRepository.findById(1).get();
		assertNotNull(customer);
		assertEquals("raju", customer.getName());
	}

	@Test
	public void testUpdate() {
		Customer customer = customerRepository.findById(1).get();
		customer.setEmail("raju.hera.pheri@gmail.com");

		customerRepository.save(customer);
	}

	@Test
	public void testDelete() {
		if (customerRepository.existsById(1)) {
			customerRepository.deleteById(1);
			System.out.println("Customer deleted !!");
		} else {
			System.out.println("Customer doesn't exist !!");
		}
	}

	@Test
	public void testCount() {
		System.out.println("Total customers :: " + customerRepository.count());
	}

	@Transactional
	@Test
	@Rollback(value = false)
	public void testUpdateEmailById() {
		customerRepository.updateEmailById(1, "raju.awasthi@gmail.com");
		System.out.println(customerRepository.findById(1));
	}
}
