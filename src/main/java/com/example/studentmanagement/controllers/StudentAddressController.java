package com.example.studentmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.models.Address;
import com.example.studentmanagement.models.Student;
import com.example.studentmanagement.payloads.MessageResponse;
import com.example.studentmanagement.repositories.StudentRepository;


@RestController
@RequestMapping("/students")
public class StudentAddressController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	@RequestMapping(value = "/{studentId}/addresses", method = RequestMethod.POST)
	public ResponseEntity<?> createAddressForStudent(@PathVariable("studentId") Long studentId,
			@RequestBody Address address) {

		Student student = studentRepository.findById(studentId).orElse(null);

		if (student == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponse("Student not found with ID " + studentId));
		} else {
			student.setAddress(address);
			
			student = studentRepository.saveAndFlush(student);

			return ResponseEntity.status(HttpStatus.OK).body(student.getAddress());
		}
	}

	
	@RequestMapping(value = "/{studentId}/addresses", method = RequestMethod.GET)
	public ResponseEntity<?> readAddressForStudent(@PathVariable("studentId") Long studentId) {

		Student student = studentRepository.findById(studentId).orElse(null);

		if (student == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponse("Student not found with ID " + studentId));
		} else {
			Address address = student.getAddress();
			return ResponseEntity.status(HttpStatus.OK).body(address);
		}
	}
}
