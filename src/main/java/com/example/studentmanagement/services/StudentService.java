package com.example.studentmanagement.services;

import org.springframework.http.ResponseEntity;

import com.example.studentmanagement.models.Student;

public interface StudentService {

	public ResponseEntity<?> createStudent(Student student);
	
	public ResponseEntity<?> readStudents();
	
	public ResponseEntity<?> readStudentById(Long id);
	
	public ResponseEntity<?> readStudentByName(String nameForSearch);
	
	public ResponseEntity<?> updateStudent(Long id, Student student);
	
	public ResponseEntity<?> deleteStudent(Long id);
}