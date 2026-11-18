package com.example.studentmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Entity class that maps to the "students" table in the database.
// Each field of this class is a column in the table.

@Entity // Marks this class as a database table
@Table(name = "students")
public class Student {

    @Id // Primary key (unique identifier)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID is generated automatically: 1, 2, 3...
    private Long id;

    @NotBlank(message = "Name is required") // Validation fails if empty
    private String name;

    @Email(message = "Please enter a valid email")
    private String email;

    @NotBlank(message = "Department is required")
    private String department;

    private Integer age;

    private Double marks;

    // No-args constructor, required by JPA
    public Student() {
    }

    // All-args constructor for creating objects easily
    public Student(String name, String email, String department, Integer age, Double marks) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.age = age;
        this.marks = marks;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }
}
