package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Idhu CONTROLLER layer.
// Idhu than OUTSIDE world (Postman, browser, frontend) kooda pesura layer.
// "URL" (endpoint) request varum bothu, edhu method call aagum nu
// idhu than decide pannum. Idhu neraya Service ah call pannum.

@RestController  // Idhu REST API responses (JSON) return pannum nu sollrom
@RequestMapping("/api/students")  // Base URL - ella endpoints-um /api/students la start aagum
public class StudentController {

    @Autowired
    private StudentService studentService;

    // GET http://localhost:8080/api/students
    // Ella students-oda list ah kudukum
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // GET http://localhost:8080/api/students/1
    // Oru particular student ID vachi kudukum
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST http://localhost:8080/api/students
    // Body la JSON data anupi pudhu student ah create panna
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        Student saved = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT http://localhost:8080/api/students/1
    // Already iruka student data ah update panna
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        try {
            return ResponseEntity.ok(studentService.updateStudent(id, student));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE http://localhost:8080/api/students/1
    // Student ah delete panna
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // GET http://localhost:8080/api/students/department/CSE
    // Department vachi filter panna
    @GetMapping("/department/{department}")
    public List<Student> getByDepartment(@PathVariable String department) {
        return studentService.getStudentsByDepartment(department);
    }

    // GET http://localhost:8080/api/students/search?name=raj
    // Name vachi search panna
    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam String name) {
        return studentService.searchByName(name);
    }
}
