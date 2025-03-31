package com.example.studentManagementApp.web.controller;

import com.example.studentManagementApp.domain.Student;
import com.example.studentManagementApp.service.StudentService;
import com.example.studentManagementApp.web.model.StudentProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project: studentManagementApp
 * Author : Hemed Awadh
 * on
 * Mar
 * 2025
 * 3/30/2025
 */
@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {

        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(student, id));

    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        return ResponseEntity.ok(studentService.getByEmail(email));
    }

    @GetMapping("/partial/{id}")
    public ResponseEntity<StudentProjection> getStudentByPartialId(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getPartialStudent(id));
    }


}
