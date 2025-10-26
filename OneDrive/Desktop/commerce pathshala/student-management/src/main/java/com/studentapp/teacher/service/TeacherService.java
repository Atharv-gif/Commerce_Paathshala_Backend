// main logic 

package com.studentapp.teacher.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studentapp.teacher.entity.Teacher;
import com.studentapp.teacher.repository.TeacherRepository;
import com.studentapp.exception.ResourceNotFoundException;

@Service


public class TeacherService{

    private final TeacherRepository teacherRepository; // this is the field 

    public TeacherService(TeacherRepository teacherRepository){ // before bracket is method and things writen under bracket is 
        this.teacherRepository = teacherRepository;
    }
     
    // CREATE 

    
    public Teacher createTeacher(Teacher teacher){
        
        // this is checking for blank name
        if (teacher.getName() == null || teacher.getName().isBlank()){
            throw new IllegalArgumentException("Teacher name cannot be blank.");
        }
        
        // for checking duplicate email
        teacherRepository.findByEmail(teacher.getEmail()).ifPresent(t -> {
            throw new IllegalArgumentException("Email" + teacher.getEmail() + "Already in use");
        });

        return teacherRepository.save(teacher);
    }


    // READ 

    //this will read all.
    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    // this will read id.
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
    }



    // update

    public Teacher updateTeacher(Long id , Teacher teacherDetails){
        Teacher existingTeacher = getTeacherById(id);

        // to ensure that teacher name should not be blank 

        if (teacherDetails.getName() == null || teacherDetails.getName().isBlank()){
            throw new IllegalArgumentException("Teacher name cannot be blank.");
        }

        // if they are chainging their email to one that's already taken

        if(!existingTeacher.getEmail().equals(teacherDetails.getEmail())){
            teacherRepository.findByEmail(teacherDetails.getEmail()).ifPresent( t ->{
                throw new IllegalArgumentException("Email" + teacherDetails.getEmail() + "this is already in use");
            });
        }


        //------- updating fields--------// 

        existingTeacher.setName(teacherDetails.getName());
        existingTeacher.setEmail(teacherDetails.getEmail());
        existingTeacher.setSubject(teacherDetails.getSubject());

        return teacherRepository.save(existingTeacher);
    }

    // delete 

    public void deleteTeacher(Long id){
        Teacher existingTeacher = getTeacherById(id);

        teacherRepository.delete(existingTeacher);
    }

}