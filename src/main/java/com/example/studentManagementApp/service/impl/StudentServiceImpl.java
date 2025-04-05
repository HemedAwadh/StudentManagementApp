package com.example.studentManagementApp.service.impl;

import com.example.studentManagementApp.domain.Student;
import com.example.studentManagementApp.exception.ResourceNotFoundException;
import com.example.studentManagementApp.repository.StudentRepository;
import com.example.studentManagementApp.service.StudentService;
import com.example.studentManagementApp.web.model.StudentProjection;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: studentManagementApp
 * Author : Hemed Awadh
 * on
 * Mar
 * 2025
 * 3/29/2025
 */
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents() {

        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " not found"));
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        Student dbStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " not found"));
//  dbStudent.setFirstName(student.getFirstName());
//  dbStudent.setLastName(student.getLastName());
        return studentRepository.save(dbStudent);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student with Email" + email + " Not Found"));

    }

    @Override
    public StudentProjection getPartialStudent(Long id) {

        return studentRepository.findStudentProjectionById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " not found"));
    }
}
