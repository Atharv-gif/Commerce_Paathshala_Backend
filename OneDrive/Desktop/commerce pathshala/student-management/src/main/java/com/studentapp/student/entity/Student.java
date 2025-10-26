package com.studentapp.student.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    @Column(name = "firebase_uid", nullable = false, unique = true)
    private String firebaseUid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

   
    @Column(name = "roll_number", unique = true)
    private String rollNumber;


    @Column(name = "class_name") 
    private String className;

    
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Student() {
    }

    // --- Getters and Setters ---
    public Long getId() { 
        return id; }

    public void setId(Long id) { 
        this.id = id; }

    public String getFirebaseUid() { 
        return firebaseUid; }

    public void setFirebaseUid(String firebaseUid) { 
        this.firebaseUid = firebaseUid; }

    public String getName() { 
        return name; }

    public void setName(String name) { 
        this.name = name; }

    public String getEmail() { 
        return email; }

    public void setEmail(String email) { 
        this.email = email; }

    public String getRollNumber() { 
        return rollNumber; }

    public void setRollNumber(String rollNumber) { 
        this.rollNumber = rollNumber; }

    public String getClassName() { 
        return className; }

    public void setClassName(String className) { 
        this.className = className; }

    public LocalDateTime getCreatedAt() { 
        return createdAt; }

    public LocalDateTime getUpdatedAt() { 
        return updatedAt; }


   
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}