package com.example.studentmanagement.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.models.Course;
import com.example.studentmanagement.models.Student;
import com.example.studentmanagement.payloads.MessageResponse;
import com.example.studentmanagement.repositories.CourseRepository;
import com.example.studentmanagement.repositories.StudentRepository;


@RestController
@RequestMapping("/students")
public class StudentCourseController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	
	@RequestMapping(value = "/{studentId}/courses", method = RequestMethod.POST)
	public ResponseEntity<?> createCourseForStudent(@PathVariable("studentId") Long studentId,
			@RequestBody Course course) {

		Student student = studentRepository.findById(studentId).orElse(null);

		if (student == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponse("Student not found with ID " + studentId));
		} else {
			Optional<Course> courseExisted  = courseRepository
					.findByCodeIgnoreCase(course.getCode());
			
			if(courseExisted.isPresent()) {
				Course courseUpdated = courseExisted.get();
				courseUpdated.setTitle(course.getTitle());
				courseUpdated.setCredit(course.getCredit());
	            
	            course = courseRepository.saveAndFlush(courseUpdated);
			}
				
			student.getCourses().add(course);
			
			student = studentRepository.saveAndFlush(student);

			return ResponseEntity.status(HttpStatus.OK).body(student.getPayments().get(student.getPayments().size() - 1));
		}
	}

	
	@RequestMapping(value = "/{studentId}/courses", method = RequestMethod.GET)
	public ResponseEntity<?> readCourseForStudent(@PathVariable("studentId") Long studentId) {

		Student student = studentRepository.findById(studentId).orElse(null);

		if (student == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponse("Student not found with ID " + studentId));
		} else {
			List<Course> courses = student.getCourses();
			return ResponseEntity.status(HttpStatus.OK).body(courses);
		}
	}
}
