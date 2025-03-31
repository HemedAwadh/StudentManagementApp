package com.example.studentManagementApp.service;

import com.example.studentManagementApp.domain.Student;
import com.example.studentManagementApp.web.model.StudentProjection;

import java.util.List;

/**
 * Project: studentManagementApp
 * Author : Hemed Awadh
 * on
 * Mar
 * 2025
 * 3/29/2025
 */

public interface StudentService {
    List<Student> getStudents();
    Student getStudentById(Long id);
    Student updateStudent(Student student,Long id);
    Student addStudent(Student student);
    Student getByEmail(String email);
    StudentProjection getPartialStudent(Long id);
}
