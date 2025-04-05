package com.example.studentManagementApp.web.controller;

import com.example.studentManagementApp.domain.Student;
import com.example.studentManagementApp.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
        // Given
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(1L);
        student.setLastName("Doe");
        student.setRegNumber("42");
        when(studentService.updateStudent(Mockito.any(), Mockito.<Long>any())).thenReturn(student);

        Student student2 = new Student();
        student2.setEmail("jane.doe@example.org");
        student2.setFirstName("Jane");
        student2.setId(1L);
        student2.setLastName("Doe");
        student2.setRegNumber("42");
        String content = (new ObjectMapper()).writeValueAsString(student2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/students/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // When and Assert
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"regNumber\":\"42\"}"));

    }

    @Test
    @DisplayName("Test createStudent(Student)")
    void testCreateStudent() throws Exception {
        // Arrange
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(1L);
        student.setLastName("Doe");
        student.setRegNumber("42");
        when(studentService.addStudent(Mockito.any())).thenReturn(student);

        Student student2 = new Student();
        student2.setEmail("jane.doe@example.org");
        student2.setFirstName("Jane");
        student2.setId(1L);
        student2.setLastName("Doe");
        student2.setRegNumber("42");
        String content = (new ObjectMapper()).writeValueAsString(student2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"regNumber\":\"42\"}"));
    }

    @Test
    @DisplayName("Test get student By email")
    void getStudentByEmail() {
    }

    @Test
    void getStudentByPartialId() {
    }
}