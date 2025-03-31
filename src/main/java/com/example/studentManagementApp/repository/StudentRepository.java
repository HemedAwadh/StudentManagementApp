package com.example.studentManagementApp.repository;

import com.example.studentManagementApp.domain.Student;
import com.example.studentManagementApp.web.model.StudentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Project: studentManagementApp
 * Author : Hemed Awadh
 * on
 * Mar
 * 2025
 * 3/29/2025
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByEmailAndRegNumber(String email, String regNumber);

    //SELECT Id ,email From STUDENTS where id = ?1;
    Optional<StudentProjection> findStudentProjectionById(Long id);
}
