package com.example.studentmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Idhu MODEL / ENTITY class.
// Idhu than database la "students" nu oru table create pannum.
// Class oda ஒவ்வொரு field-um, table oda ஒரு column.

@Entity  // Idhu oru database table nu Spring ku sollrom
@Table(name = "students")
public class Student {

    @Id  // Idhu Primary Key (unique identifier)
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Automatic aa 1,2,3.. nu id generate aagum
    private Long id;

    @NotBlank(message = "Name kandippa kudukanum")  // Empty aa iruntha error varum
    private String name;

    @Email(message = "Sariyana email kudunga")
    private String email;

    @NotBlank(message = "Department kandippa kudukanum")
    private String department;

    private Integer age;

    private Double marks;

    // Empty constructor - JPA ku idhu kandippa venum
    public Student() {
    }

    // All-args constructor - object create panna easy aa irukum
    public Student(String name, String email, String department, Integer age, Double marks) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.age = age;
        this.marks = marks;
    }

    // Getters and Setters - ovvoru field oda value ah edukkavum, set pannavum
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
