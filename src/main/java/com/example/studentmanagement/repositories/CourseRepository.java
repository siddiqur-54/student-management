package com.example.studentmanagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentmanagement.models.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	
	Optional<Course> findByCodeIgnoreCase(String code);
}
