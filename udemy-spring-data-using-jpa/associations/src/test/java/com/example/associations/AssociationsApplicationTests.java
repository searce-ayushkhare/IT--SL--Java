package com.example.associations;

import com.example.associations.manytomany.entities.Programmer;
import com.example.associations.manytomany.entities.Project;
import com.example.associations.manytomany.repositories.ProgrammerRepository;
import com.example.associations.onetomany.entities.Customer;
import com.example.associations.onetomany.entities.PhoneNumber;
import com.example.associations.onetomany.repositories.CustomerRepository;
import com.example.associations.onetoone.entities.License;
import com.example.associations.onetoone.entities.Person;
import com.example.associations.onetoone.repositories.LicenseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;

@SpringBootTest
class AssociationsApplicationTests {

	@Autowired private CustomerRepository customerRepository;
	@Autowired private ProgrammerRepository programmerRepository;
	@Autowired private LicenseRepository licenseRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setName("johnny");

		//HashSet<PhoneNumber> numbers = new HashSet<>();

		PhoneNumber ph1 = new PhoneNumber();
		ph1.setNumber("1234567890");
		ph1.setType("cell");
		//ph1.setCustomer(customer);
		//numbers.add(ph1);

		PhoneNumber ph2 = new PhoneNumber();
		ph2.setNumber("0987654321");
		ph2.setType("home");
		//ph2.setCustomer(customer);
		//numbers.add(ph2);

		//customer.setNumbers(numbers);
		customer.addPhoneNumbers(ph1);
		customer.addPhoneNumbers(ph2);

		customerRepository.save(customer);
	}

	@Test
	@Transactional
	public void testLoadCustomer() {
		Customer customer = customerRepository.findById(4).get();
		System.out.println(customer.getName());

		customer.getNumbers().forEach(ph -> System.out.println(ph.getNumber()));
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = customerRepository.findById(4).get();
		customer.setName("Shinchan");

		customer.getNumbers().forEach(number -> number.setType("work"));

		customerRepository.save(customer);
	}

	@Test
	public void testDeleteCustomer() {
		customerRepository.deleteById(3);
	}

	@Test
	public void testmtomCreateProgrammer() {
		Programmer programmer = new Programmer();
		programmer.setName("yaku");
		programmer.setSalary(100000);

		Project project = new Project();
		project.setName("hibernate");

		HashSet<Project> projects = new HashSet<>();
		projects.add(project);

		programmer.setProjects(projects);

		programmerRepository.save(programmer);
	}

	@Test
	@Transactional
	public void testmtomFindProgrammer() {
		Programmer programmer = programmerRepository.findById(2).get();
		System.out.println(programmer);
		System.out.println(programmer.getProjects());
	}

	@Test
	public void test1to1CreateLicense() {
		License license = new License();
		license.setType("CAR");
		license.setValidFrom(new Date());
		license.setValidTo(new Date());

		Person person = new Person();
		person.setFirstName("Johnny");
		person.setLastName("Walker");
		person.setAge(53);

		license.setPerson(person);

		licenseRepository.save(license);
	}
}
