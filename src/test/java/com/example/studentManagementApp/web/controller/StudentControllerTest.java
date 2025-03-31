package com.example.studentManagementApp.web.controller;

import com.example.studentManagementApp.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
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
    @MockBean
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

//    @Test
//    void getStudentById() {
//    }
//
//    @Test
//    void updateStudent() {
//    }
//
//    @Test
//    void createStudent() {
//    }
//
//    @Test
//    void getStudentByEmail() {
//    }
//
//    @Test
//    void getStudentByPartialId() {
//    }
}