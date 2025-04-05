package com.example.studentManagementApp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Project: studentManagementApp
 * Author : Hemed Awadh
 * on
 * Mar
 * 2025
 * 3/29/2025
 */
@Entity
@Table(name="STUDENTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String firstName;
 private String lastName;
 private String email;
 private String regNumber;
}
