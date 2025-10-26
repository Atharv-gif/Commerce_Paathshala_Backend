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

    //  CREATE 
    
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    //  READ (All) ---
     
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    //  READ (by ID) ---
    
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // UPDATE 
    
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        return studentService.updateStudent(id, studentDetails);
    }

    //  DELETE 
    
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student with id: " + id + " has been deleted successfully.";
    }
}