//it will held communication with service

package com.studentapp.teacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.studentapp.teacher.entity.Teacher;

@Repository // from here spring can find your repository and it makes database error simple and clean 


public interface TeacherRepository extends JpaRepository<Teacher , Long>{ // with the help of inheritance we can use CRUD

    Optional<Teacher> findByEmail(String email); // searches the database to find that one specific email.
}
