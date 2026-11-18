package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository layer.
// Talks directly to the database (save, find, update, delete).
// By extending JpaRepository, methods like save(), findAll(), findById()
// and deleteById() are provided automatically, with no extra code.

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom query method: finds students by department.
    // Spring generates the query automatically from the method name
    // (this is called a "query method").
    List<Student> findByDepartment(String department);

    // Searches by name (case-insensitive, matches if the name contains the keyword)
    List<Student> findByNameContainingIgnoreCase(String name);
}
