package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Idhu REPOSITORY layer.
// Idhu than DIRECT aa database oda pesum (save, find, delete, update).
// JpaRepository extend panna, namba onnume code pannama
// save(), findAll(), findById(), deleteById() mathiri methods automatic aa kidaikum!

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom method - department vachi students ah thedanum nu vachu
    // Method name mattum correct aa irunthaal, Spring automatic aa
    // query create pannikum (idhu "Query Method" nu solluvanga)
    List<Student> findByDepartment(String department);

    // Name la search panna (contains - andha keyword iruntha match aagum)
    List<Student> findByNameContainingIgnoreCase(String name);
}
