package com.example.jpqlandnativesql.repositories;

import com.example.jpqlandnativesql.entities.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("FROM Student")
    List<Student> findAllStudents(Pageable pageable);

    @Query("SELECT st.firstName, st.lastName FROM Student st")
    List<Object[]> findAllStudentsPartialData();

    @Query("FROM Student WHERE firstName =:firstNameParam")
    List<Student> findAllStudentsByFirstName(@Param("firstNameParam") String firstName);

    @Query("FROM Student WHERE score >=:minScore AND score <=:maxScore")
    List<Student> findStudentsForGivenScore(@Param("minScore") int min, @Param("maxScore") int max);

    @Modifying
    @Query("DELETE FROM Student WHERE firstName =:firstNameParam")
    void deleteStudentsByFirstName(@Param("firstNameParam") String firstName);

    @Query(value = "SELECT * FROM student", nativeQuery = true)
    List<Student> findAllStudentsNQ();

    @Query(value = "SELECT * FROM student WHERE fname =:firstNameParam", nativeQuery = true)
    List<String> findByFirstNameNQ(@Param("firstNameParam") String firstName);
}
