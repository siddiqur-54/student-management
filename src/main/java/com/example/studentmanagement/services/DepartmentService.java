package com.example.studentmanagement.services;

import org.springframework.http.ResponseEntity;

import com.example.studentmanagement.models.Department;

public interface DepartmentService {

	public ResponseEntity<?> createDeaprtment(Department department);

	public ResponseEntity<?> readDepartments();

	public ResponseEntity<?> readDepartmentById(Long id);
}
