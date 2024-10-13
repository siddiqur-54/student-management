package com.example.studentmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.models.Course;
import com.example.studentmanagement.services.CourseService;


@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createCourse(@RequestBody Course course) {
		return courseService.createCourse(course);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> readCourses() {
		return courseService.readCourses();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> readDepartmentById(@PathVariable("id") Long id) {
		return courseService.readCourseById(id);
	}
}
