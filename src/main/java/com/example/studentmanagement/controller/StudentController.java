package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller layer.
// Handles incoming requests from clients (Postman, browser, front end).
// Decides which method runs for each URL (endpoint) and delegates
// the work to the Service layer.

@RestController // Returns REST API responses (JSON)
@RequestMapping("/api/students") // Base URL: all endpoints start with /api/students
public class StudentController {

    @Autowired
    private StudentService studentService;

    // GET http://localhost:8080/api/students
    // Returns the list of all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // GET http://localhost:8080/api/students/1
    // Returns a single student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST http://localhost:8080/api/students
    // Creates a new student from the JSON request body
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        Student saved = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT http://localhost:8080/api/students/1
    // Updates an existing student
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        try {
            return ResponseEntity.ok(studentService.updateStudent(id, student));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE http://localhost:8080/api/students/1
    // Deletes a student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // GET http://localhost:8080/api/students/department/CSE
    // Filters students by department
    @GetMapping("/department/{department}")
    public List<Student> getByDepartment(@PathVariable String department) {
        return studentService.getStudentsByDepartment(department);
    }

    // GET http://localhost:8080/api/students/search?name=raj
    // Searches students by name
    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam String name) {
        return studentService.searchByName(name);
    }
}
