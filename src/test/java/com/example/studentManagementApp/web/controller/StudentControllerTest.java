package com.example.studentManagementApp.web.controller;

import com.example.studentManagementApp.domain.Student;
import com.example.studentManagementApp.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Project: studentManagementApp
 * Author : Hemed Awadh
 * on
 * Mar
 * 2025
 * 3/31/2025
 */
@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class StudentControllerTest {
    @Autowired
    private StudentController studentController;
    @MockitoBean
    private StudentService studentService;

    @Test
    @DisplayName("Test get All Students")
    void getAllStudents() throws Exception {
        //given
        when(studentService.getStudents()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/api/v1/students");
        //When & Assert
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(getRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));


    }

    @Test
    @DisplayName("Test get Students By id")
    void getStudentById() throws Exception {
        //given
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        when(studentService.getStudentById(1L)).thenReturn(student);
        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/api/v1/students/1");
        //When && assert
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(getRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"));


    }

    @Test
    @DisplayName("Test update Students")
    void updateStudent() throws Exception {
        //given
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");

        when(studentService.updateStudent(student, student.getId())).thenReturn(student);
        String studentJson = new ObjectMapper().writeValueAsString(student);

        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders
                .put("/api/v1/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentJson);
        //When && assert
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(getRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"));


    }

    @Test
    void createStudent() {
    }

    @Test
    void getStudentByEmail() {
    }

    @Test
    void getStudentByPartialId() {
    }
}