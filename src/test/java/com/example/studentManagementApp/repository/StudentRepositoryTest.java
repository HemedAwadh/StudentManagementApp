package com.example.studentManagementApp.repository;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.studentManagementApp.domain.Student;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {StudentRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.example.studentManagementApp.domain"})
@DataJpaTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("Test findByEmail(String)")
    void testFindByEmail() {
        // Arrange
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setLastName("Doe");
        student.setRegNumber("42");

        Student student2 = new Student();
        student2.setEmail("john.smith@example.org");
        student2.setFirstName("John");
        student2.setLastName("Smith");
        student2.setRegNumber("Reg Number");
        studentRepository.save(student);
        studentRepository.save(student2);

        // Act
        Optional<Student> actualFindByEmailResult = studentRepository.findByEmail("jane.doe@example.org");

        // Assert
        assertTrue(actualFindByEmailResult.isPresent());
        assertSame(student, actualFindByEmailResult.get());
    }

    @Test
    @DisplayName("Test findByEmailAndRegNumber(String, String)")
    void testFindByEmailAndRegNumber() {
        // Arrange
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setLastName("Doe");
        student.setRegNumber("42");

        Student student2 = new Student();
        student2.setEmail("john.smith@example.org");
        student2.setFirstName("John");
        student2.setLastName("Smith");
        student2.setRegNumber("Reg Number");
        studentRepository.save(student);
        studentRepository.save(student2);

        // Act
        Optional<Student> actualFindByEmailAndRegNumberResult = studentRepository
                .findByEmailAndRegNumber("jane.doe@example.org", "42");

        // Assert
        assertTrue(actualFindByEmailAndRegNumberResult.isPresent());
        assertSame(student, actualFindByEmailAndRegNumberResult.get());
    }

    @Test
    @DisplayName("Test findStudentProjectionById(Long)")
    void testFindStudentProjectionById() {
        // Arrange
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setLastName("Doe");
        student.setRegNumber("42");
        studentRepository.save(student);

        Student student2 = new Student();
        student2.setEmail("john.smith@example.org");
        student2.setFirstName("John");
        student2.setLastName("Smith");
        student2.setRegNumber("Reg Number");
        studentRepository.save(student2);

        Student student3 = new Student();
        student3.setEmail("jane.doe@example.org");
        student3.setFirstName("Jane");
        student3.setLastName("Doe");
        student3.setRegNumber("42");
        studentRepository.save(student3);

        // Act and Assert
        assertTrue(studentRepository.findStudentProjectionById(student3.getId()).isPresent());
    }
}
