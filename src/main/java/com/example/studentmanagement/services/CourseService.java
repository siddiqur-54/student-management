package com.example.studentmanagement.services;

import org.springframework.http.ResponseEntity;

import com.example.studentmanagement.models.Course;


public interface CourseService {
	
	public ResponseEntity<?> createCourse(Course course);

	public ResponseEntity<?> readCourses();

	public ResponseEntity<?> readCourseById(Long id);
}
