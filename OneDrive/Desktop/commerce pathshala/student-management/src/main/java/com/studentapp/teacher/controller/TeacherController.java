package com.studentapp.teacher.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentapp.teacher.entity.Teacher;
import com.studentapp.teacher.service.TeacherService;

@RestController //handle  request and sends jason data 
@RequestMapping("/api/teachers") // base url 
public class TeacherController {

    private final TeacherService teacherService; // this is field which will hold TeacherService

    public TeacherController(TeacherService teacherService){ //TeacherService is parameter
        this.teacherService = teacherService; //this will put parameter into field 
    }

    // create 

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher){ // When JSON data comes in, it will convert that JSON into a Teacher object and give that object to me through the teacher variable.
        return teacherService.createTeacher(teacher); // It will pass the teacher to Service 
    }

    // Read (read All)

    @GetMapping
    public List<Teacher> getAllTeacher(){
        return teacherService.getAllTeachers();
    }
    
    // -----Read by id------
    
    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable Long id){
        return teacherService.getTeacherById(id);
    }

    // Update

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable Long id , @RequestBody Teacher teacherDetails){
        return teacherService.updateTeacher(id,teacherDetails);
        
    }

    //Delete

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable Long id){
        teacherService.deleteTeacher(id);
        return "Teacher with id"  + id +  "has been deleted";
    }
}