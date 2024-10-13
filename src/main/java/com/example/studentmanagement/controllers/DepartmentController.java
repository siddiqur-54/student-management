package com.example.studentmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.models.Department;
import com.example.studentmanagement.services.DepartmentService;


@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createDepartment(@RequestBody Department department) {
		return departmentService.createDeaprtment(department);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> readDepartments() {
		return departmentService.readDepartments();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> readDepartmentById(@PathVariable("id") Long id) {
		return departmentService.readDepartmentById(id);
	}
}
