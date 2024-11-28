package com.example.crud_operation_json.service;


import com.example.crud_operation_json.model.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final String dataFilePath = "src/main/resources/students.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Fetch all students
    public List<Student> getAllStudents() throws IOException {
        File file = new File(dataFilePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<Student>>() {});
    }

    // Write updated students to file
    private void writeStudentsToFile(List<Student> students) throws IOException {
        objectMapper.writeValue(new File(dataFilePath), students);
    }

    // Get a student by ID
    public Optional<Student> getStudentById(int id) throws IOException {
        return getAllStudents().stream().filter(student -> student.getId() == id).findFirst();
    }

    // Create a new student
    public void addStudent(Student newStudent) throws IOException {
        List<Student> students = getAllStudents();
        boolean exists = students.stream().anyMatch(student -> student.getId() == newStudent.getId());
        if (exists) {
            throw new RuntimeException("Student with ID " + newStudent.getId() + " already exists.");
        }
        students.add(newStudent);
        writeStudentsToFile(students);
    }

    // Update an existing student
    public boolean updateStudent(int id, Student updatedStudent) throws IOException {
        List<Student> students = getAllStudents();
        Optional<Student> existingStudent = students.stream().filter(student -> student.getId() == id).findFirst();
        if (existingStudent.isPresent()) {
            students.remove(existingStudent.get());
            students.add(updatedStudent);
            writeStudentsToFile(students);
            return true;
        }
        return false;
    }

    // Delete a student by ID
    public boolean deleteStudent(int id) throws IOException {
        List<Student> students = getAllStudents();
        Optional<Student> existingStudent = students.stream().filter(student -> student.getId() == id).findFirst();
        if (existingStudent.isPresent()) {
            students.remove(existingStudent.get());
            writeStudentsToFile(students);
            return true;
        }
        return false;
    }
}
