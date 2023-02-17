package com.example.jpqlandnativesql;

import com.example.jpqlandnativesql.entities.Student;
import com.example.jpqlandnativesql.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class JpqlandnativesqlApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreate() {
		Student student = new Student();
		student.setFirstName("sameer");
		student.setLastName("soni");
		student.setScore(79);

		studentRepository.save(student);
	}

	@Test
	public void testFindAllStudents() {
		System.out.println(studentRepository.findAllStudents(PageRequest.of(0,2, Sort.Direction.DESC, "id")));
	}

	@Test
	public void testFindAllStudentsPartialData() {
		studentRepository
				.findAllStudentsPartialData()
				.forEach(st -> System.out.println(st[0] + " " + st[1]));
	}

	@Test
	public void testFindAllStudentsByFirstname() {
		System.out.println(studentRepository.findAllStudentsByFirstName("raju"));
	}

	@Test
	public void testFindStudentsForGivenScore() {
		System.out.println(studentRepository.findStudentsForGivenScore(80, 100));
	}

	@Test
	@Transactional
	@Rollback(value = false)
	public void testDeleteStudentByFirstName() {
		studentRepository.deleteStudentsByFirstName("raju");
	}

	@Test
	public void testFindAllStudentsNQ() {
		System.out.println(studentRepository.findAllStudentsNQ());
	}

	@Test
	public void testFindByFirstName() {
		System.out.println(studentRepository.findByFirstNameNQ("yash"));
	}
}
