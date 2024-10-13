package com.example.studentmanagement.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.models.Department;
import com.example.studentmanagement.repositories.DepartmentRepository;
import com.example.studentmanagement.services.DepartmentService;

@Service
public class DepartmentServiceImplementation implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public ResponseEntity<?> createDeaprtment(Department department) {
		department = departmentRepository.saveAndFlush(department);
		return ResponseEntity.status(HttpStatus.CREATED).body(department);
	}

	public ResponseEntity<?> readDepartments() {
		List<Department> departments = departmentRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(departments);
	}

	public ResponseEntity<?> readDepartmentById(Long id) {
		Department department = departmentRepository.findById(id).orElse(null);
		if(department != null) {
			return ResponseEntity.status(HttpStatus.OK).body(department);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not found with ID: " + id);
		}
	}
}
