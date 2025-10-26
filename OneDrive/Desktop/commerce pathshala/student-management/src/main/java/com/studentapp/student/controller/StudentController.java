package com.studentapp.student.controller;

import com.studentapp.student.entity.Student;
import com.studentapp.student.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // --- 1. CREATE Student ---
    // POST /api/students
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    // --- 2. READ (All) ---
    // GET /api/students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // --- 3. READ (by ID) ---
    // GET /api/students/1
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // --- 4. UPDATE Student ---
    // PUT /api/students/1
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        return studentService.updateStudent(id, studentDetails);
    }

    // --- 5. DELETE Student ---
    // DELETE /api/students/1
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student with id: " + id + " has been deleted successfully.";
    }
}