package com.example.studentmanagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentmanagement.models.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	Optional<Student> findStudentByEmail(String email);
	Optional<Student> findStudentByStudentId(String studentId);
	List<Student> findByNameContainingIgnoreCase(String name);
}
