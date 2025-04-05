package com.example.studentManagementApp.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.studentManagementApp.domain.Student;
import com.example.studentManagementApp.exception.ResourceNotFoundException;
import com.example.studentManagementApp.repository.StudentRepository;
import com.example.studentManagementApp.web.model.StudentProjection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StudentServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class StudentServiceImplTest {
    @MockitoBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Test
    @DisplayName("Test getStudents(); given StudentRepository findAll() return ArrayList(); then return Empty")
    void testGetStudents_givenStudentRepositoryFindAllReturnArrayList_thenReturnEmpty() {
        // Arrange
        when(studentRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Student> actualStudents = studentServiceImpl.getStudents();

        // Assert
        verify(studentRepository).findAll();
        assertTrue(actualStudents.isEmpty());
    }

    @Test
    @DisplayName("Test getStudents(); then throw ResourceNotFoundException")
    void testGetStudents_thenThrowResourceNotFoundException() {
        // Arrange
        when(studentRepository.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> studentServiceImpl.getStudents());
        verify(studentRepository).findAll();
    }

    @Test
    @DisplayName("Test getStudentById(Long)")
    void testGetStudentById() {
        // Arrange
        when(studentRepository.findById(Mockito.<Long>any())).thenThrow(new ResourceNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> studentServiceImpl.getStudentById(1L));
        verify(studentRepository).findById(eq(1L));
    }

    @Test
    @DisplayName("Test getStudentById(Long); given Student() Email is 'jane.doe@example.org'; then return Student()")
    void testGetStudentById_givenStudentEmailIsJaneDoeExampleOrg_thenReturnStudent() {
        // Arrange
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(1L);
        student.setLastName("Doe");
        student.setRegNumber("42");
        Optional<Student> ofResult = Optional.of(student);
        when(studentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Student actualStudentById = studentServiceImpl.getStudentById(1L);

        // Assert
        verify(studentRepository).findById(eq(1L));
        assertSame(student, actualStudentById);
    }


    @Test
    @DisplayName("Test getStudentById(Long); given StudentRepository findById(Object) return empty")
    void testGetStudentById_givenStudentRepositoryFindByIdReturnEmpty() {
        // Arrange
        Optional<Student> emptyResult = Optional.empty();
        when(studentRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> studentServiceImpl.getStudentById(1L));
        verify(studentRepository).findById(eq(1L));
    }

    @Test
    @DisplayName("Test updateStudent(Student, Long)")
    void testUpdateStudent() {
        // Arrange
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(1L);
        student.setLastName("Doe");
        student.setRegNumber("42");
        Optional<Student> ofResult = Optional.of(student);
        when(studentRepository.save(Mockito.<Student>any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(studentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Student student2 = new Student();
        student2.setEmail("jane.doe@example.org");
        student2.setFirstName("Jane");
        student2.setId(1L);
        student2.setLastName("Doe");
        student2.setRegNumber("42");

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> studentServiceImpl.updateStudent(student2, 1L));
        verify(studentRepository).findById(eq(1L));
        verify(studentRepository).save(isA(Student.class));
    }

    @Test
    @DisplayName("Test updateStudent(Student, Long); given StudentRepository findById(Object) return empty")
    void testUpdateStudent_givenStudentRepositoryFindByIdReturnEmpty() {
        // Arrange
        Optional<Student> emptyResult = Optional.empty();
        when(studentRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(1L);
        student.setLastName("Doe");
        student.setRegNumber("42");

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> studentServiceImpl.updateStudent(student, 1L));
        verify(studentRepository).findById(eq(1L));
    }

    @Test
    @DisplayName("Test updateStudent(Student, Long); given StudentRepository save(Object) return Student(); then return Student()")
    void testUpdateStudent_givenStudentRepositorySaveReturnStudent_thenReturnStudent() {
        // Arrange
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(1L);
        student.setLastName("Doe");
        student.setRegNumber("42");
        Optional<Student> ofResult = Optional.of(student);

        Student student2 = new Student();
        student2.setEmail("jane.doe@example.org");
        student2.setFirstName("Jane");
        student2.setId(1L);
        student2.setLastName("Doe");
        student2.setRegNumber("42");
        when(studentRepository.save(Mockito.<Student>any())).thenReturn(student2);
        when(studentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Student student3 = new Student();
        student3.setEmail("jane.doe@example.org");
        student3.setFirstName("Jane");
        student3.setId(1L);
        student3.setLastName("Doe");
        student3.setRegNumber("42");

        // Act
        Student actualUpdateStudentResult = studentServiceImpl.updateStudent(student3, 1L);

        // Assert
        verify(studentRepository).findById(eq(1L));
        verify(studentRepository).save(isA(Student.class));
        assertSame(student2, actualUpdateStudentResult);
    }

    @Test
    @DisplayName("Test addStudent(Student); given Student() Email is 'jane.doe@example.org'; then return Student()")
    void testAddStudent_givenStudentEmailIsJaneDoeExampleOrg_thenReturnStudent() {
        // Arrange
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(1L);
        student.setLastName("Doe");
        student.setRegNumber("42");
        when(studentRepository.save(Mockito.<Student>any())).thenReturn(student);

        Student student2 = new Student();
        student2.setEmail("jane.doe@example.org");
        student2.setFirstName("Jane");
        student2.setId(1L);
        student2.setLastName("Doe");
        student2.setRegNumber("42");

        // Act
        Student actualAddStudentResult = studentServiceImpl.addStudent(student2);

        // Assert
        verify(studentRepository).save(isA(Student.class));
        assertSame(student, actualAddStudentResult);
    }

    @Test
    @DisplayName("Test addStudent(Student); then throw ResourceNotFoundException")
    void testAddStudent_thenThrowResourceNotFoundException() {
        // Arrange
        when(studentRepository.save(Mockito.<Student>any())).thenThrow(new ResourceNotFoundException("An error occurred"));

        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(1L);
        student.setLastName("Doe");
        student.setRegNumber("42");

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> studentServiceImpl.addStudent(student));
        verify(studentRepository).save(isA(Student.class));
    }

    @Test
    @DisplayName("Test getByEmail(String)")
    void testGetByEmail() {
        // Arrange
        when(studentRepository.findByEmail(Mockito.<String>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> studentServiceImpl.getByEmail("jane.doe@example.org"));
        verify(studentRepository).findByEmail(eq("jane.doe@example.org"));
    }

    @Test
    @DisplayName("Test getByEmail(String); given Student() Email is 'jane.doe@example.org'; then return Student()")
    void testGetByEmail_givenStudentEmailIsJaneDoeExampleOrg_thenReturnStudent() {
        // Arrange
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(1L);
        student.setLastName("Doe");
        student.setRegNumber("42");
        Optional<Student> ofResult = Optional.of(student);
        when(studentRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Student actualByEmail = studentServiceImpl.getByEmail("jane.doe@example.org");

        // Assert
        verify(studentRepository).findByEmail(eq("jane.doe@example.org"));
        assertSame(student, actualByEmail);
    }

    @Test
    @DisplayName("Test getByEmail(String); given StudentRepository findByEmail(String) return empty")
    void testGetByEmail_givenStudentRepositoryFindByEmailReturnEmpty() {
        // Arrange
        Optional<Student> emptyResult = Optional.empty();
        when(studentRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> studentServiceImpl.getByEmail("jane.doe@example.org"));
        verify(studentRepository).findByEmail(eq("jane.doe@example.org"));
    }

    @Test
    @DisplayName("Test getPartialStudent(Long)")
    void testGetPartialStudent() {
        // Arrange
        Optional<StudentProjection> ofResult = Optional.of(mock(StudentProjection.class));
        when(studentRepository.findStudentProjectionById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        studentServiceImpl.getPartialStudent(1L);

        // Assert
        verify(studentRepository).findStudentProjectionById(eq(1L));
    }

    @Test
    @DisplayName("Test getPartialStudent(Long)")
    void testGetPartialStudent2() {
        // Arrange
        when(studentRepository.findStudentProjectionById(Mockito.<Long>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> studentServiceImpl.getPartialStudent(1L));
        verify(studentRepository).findStudentProjectionById(eq(1L));
    }

    @Test
    @DisplayName("Test getPartialStudent(Long); given StudentRepository findStudentProjectionById(Long) return empty")
    void testGetPartialStudent_givenStudentRepositoryFindStudentProjectionByIdReturnEmpty() {
        // Arrange
        Optional<StudentProjection> emptyResult = Optional.empty();
        when(studentRepository.findStudentProjectionById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> studentServiceImpl.getPartialStudent(1L));
        verify(studentRepository).findStudentProjectionById(eq(1L));
    }
}
