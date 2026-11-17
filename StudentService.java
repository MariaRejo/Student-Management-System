package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Idhu SERVICE layer.
// Idhu than BUSINESS LOGIC irukura idam.
// Controller neraya Repository ah call pannadhu, Service vazhi than pannum.
// "Enna panna vendum" nu logic ellam idha than eluthuvom
// (e.g., validation, calculation, multiple repository calls combine panradhu)

@Service  // Idhu oru Service class nu Spring ku sollrom
public class StudentService {

    @Autowired  // Spring automatic aa StudentRepository object ah inject pannum
    private StudentRepository studentRepository;

    // Ella students-um edukka
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Oru particular student ah ID vachi edukka
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Pudhu student ah save panna
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Already iruka student ah update panna
    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id).map(student -> {
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            student.setDepartment(updatedStudent.getDepartment());
            student.setAge(updatedStudent.getAge());
            student.setMarks(updatedStudent.getMarks());
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // Student ah delete panna
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // Department vachi students ah thedanum
    public List<Student> getStudentsByDepartment(String department) {
        return studentRepository.findByDepartment(department);
    }

    // Name vachi search panna
    public List<Student> searchByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }
}
