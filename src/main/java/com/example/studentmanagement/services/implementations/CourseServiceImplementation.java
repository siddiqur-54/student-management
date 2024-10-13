package com.example.studentmanagement.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.models.Course;
import com.example.studentmanagement.repositories.CourseRepository;
import com.example.studentmanagement.services.CourseService;


@Service
public class CourseServiceImplementation implements CourseService{

	@Autowired
	private CourseRepository courseRepository;
	
	public ResponseEntity<?> createCourse(Course course) {
		course = courseRepository.saveAndFlush(course);
		return ResponseEntity.status(HttpStatus.CREATED).body(course);
	}

	public ResponseEntity<?> readCourses() {
		List<Course> courses = courseRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(courses);
	}

	public ResponseEntity<?> readCourseById(Long id) {
		Course course = courseRepository.findById(id).orElse(null);
		if(course != null) {
			return ResponseEntity.status(HttpStatus.OK).body(course);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found with ID: " + id);
		}
	}
}
