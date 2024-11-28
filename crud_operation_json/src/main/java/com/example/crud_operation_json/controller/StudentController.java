package com.example.crud_operation_json.controller;

import com.example.crud_operation_json.model.Student;
import com.example.crud_operation_json.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Fetch all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return 204 if no content
            }
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Handle server error
        }
    }

    // Fetch a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        try {
            Optional<Student> student = studentService.getStudentById(id);
            if (student.isPresent()) {
                return new ResponseEntity<>(student.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if student not found
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Handle server error
        }
    }

    // Add a new student
    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody Student newStudent) {
        try {
            studentService.addStudent(newStudent);
            return new ResponseEntity<>("Student added successfully", HttpStatus.CREATED);  // Return 201 if created
        } catch (IOException e) {
            return new ResponseEntity<>("Error saving student: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  // Handle IO error
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);  // Handle validation or other issues
        }
    }

    // Update a student
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        try {
            boolean updated = studentService.updateStudent(id, updatedStudent);
            if (updated) {
                return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);  // Return 200 if updated
            } else {
                return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);  // Return 404 if student not found
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Error updating student: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  // Handle IO error
        }
    }

    // Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        try {
            boolean deleted = studentService.deleteStudent(id);
            if (deleted) {
                return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);  // Return 200 if deleted
            } else {
                return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);  // Return 404 if student not found
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Error deleting student: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  // Handle IO error
        }
    }
}
