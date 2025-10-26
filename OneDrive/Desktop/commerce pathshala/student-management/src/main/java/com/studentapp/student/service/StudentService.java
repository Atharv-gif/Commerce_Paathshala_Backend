package com.studentapp.student.service;

import com.studentapp.exception.ResourceNotFoundException;
import com.studentapp.student.entity.Student;
import com.studentapp.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //  CREATE Student 

    public Student createStudent(Student student) {
        //  for blank name
        if (student.getName() == null || student.getName().isBlank()) {
            throw new IllegalArgumentException("Student name cannot be blank.");
        }

        //  for duplicate email
        studentRepository.findByEmail(student.getEmail()).ifPresent(s -> {
            throw new IllegalArgumentException("Email '" + student.getEmail() + "' is already in use.");
        });

        //  for duplicate roll number (if provided)
        if (student.getRollNumber() != null && !student.getRollNumber().isBlank()) {
            studentRepository.findByRollNumber(student.getRollNumber()).ifPresent(s -> {
                throw new IllegalArgumentException("Roll Number '" + student.getRollNumber() + "' is already in use.");
            });
        }

        return studentRepository.save(student);
    }

    // Get All
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    //  UPDATE Student 
    

    public Student updateStudent(Long id, Student studentDetails) {
        // First, find the existing student
        Student existingStudent = getStudentById(id); // This already handles "not found"

        // for blank name
        if (studentDetails.getName() == null || studentDetails.getName().isBlank()) {
            throw new IllegalArgumentException("Student name cannot be blank.");
        }

        // email to one that's already taken
        if (!existingStudent.getEmail().equals(studentDetails.getEmail())) {
            studentRepository.findByEmail(studentDetails.getEmail()).ifPresent(s -> {
                throw new IllegalArgumentException("Email '" + studentDetails.getEmail() + "' is already in use.");
            });
        }
        
        // roll number to one that's already taken
        if (studentDetails.getRollNumber() != null && !studentDetails.getRollNumber().isBlank() && 
            !existingStudent.getRollNumber().equals(studentDetails.getRollNumber())) {
            
            studentRepository.findByRollNumber(studentDetails.getRollNumber()).ifPresent(s -> {
                throw new IllegalArgumentException("Roll Number '" + studentDetails.getRollNumber() + "' is already in use.");
            });
        }

        // Update the fields
        existingStudent.setName(studentDetails.getName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setRollNumber(studentDetails.getRollNumber());
        existingStudent.setClassName(studentDetails.getClassName());
        

        
        return studentRepository.save(existingStudent);
    }

    // --- 5. DELETE Student ---
    public void deleteStudent(Long id) {
        // First, check if the student exists
        Student existingStudent = getStudentById(id); // This handles "not found"
        
        // If found, delete it
        studentRepository.delete(existingStudent);
    }
}