package com.example.studentmanagement.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.models.Department;
import com.example.studentmanagement.models.Student;
import com.example.studentmanagement.payloads.MessageResponse;
import com.example.studentmanagement.repositories.DepartmentRepository;
import com.example.studentmanagement.repositories.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentDepartmentController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	
	@RequestMapping(value = "/{studentId}/departments", method = RequestMethod.POST)
	public ResponseEntity<?> createDepartmentForStudent(@PathVariable("studentId") Long studentId,
			@RequestBody Department department) {

		Student student = studentRepository.findById(studentId).orElse(null);

		if (student == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponse("Student not found with ID " + studentId));
		} else {
			Optional<Department> departmentExisted  = departmentRepository
					.findByCodeIgnoreCase(department.getCode());
			
			if(departmentExisted.isPresent()) {
				Department departmentUpdated = departmentExisted.get();
				departmentUpdated.setName(department.getName());
				departmentUpdated.setHead(department.getHead());
	            
	            department = departmentRepository.saveAndFlush(departmentUpdated);
			}
				
			student.setDepartment(department);
			
			student = studentRepository.saveAndFlush(student);

			return ResponseEntity.status(HttpStatus.OK).body(student.getDepartment());
		}
	}

	
	@RequestMapping(value = "/{studentId}/departments", method = RequestMethod.GET)
	public ResponseEntity<?> readDepartmentForStudent(@PathVariable("studentId") Long studentId) {

		Student student = studentRepository.findById(studentId).orElse(null);

		if (student == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponse("Student not found with ID " + studentId));
		} else {
			Department department = student.getDepartment();
			return ResponseEntity.status(HttpStatus.OK).body(department);
		}
	}
}
