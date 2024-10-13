package com.example.studentmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.models.Payment;
import com.example.studentmanagement.models.Student;
import com.example.studentmanagement.payloads.MessageResponse;
import com.example.studentmanagement.repositories.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentPaymentController {

	@Autowired
	private StudentRepository studentRepository;

	
	@RequestMapping(value = "/{studentId}/payments", method = RequestMethod.POST)
	public ResponseEntity<?> createPaymentForStudent(@PathVariable("studentId") Long studentId,
			@RequestBody Payment payment) {

		Student student = studentRepository.findById(studentId).orElse(null);

		if (student == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponse("Student not found with ID " + studentId));
		} else {
			student.getPayments().add(payment);

			student = studentRepository.saveAndFlush(student);
			return ResponseEntity.status(HttpStatus.OK).body(student.getPayments().get(student.getPayments().size() - 1));
		}
	}

	
	@RequestMapping(value = "/{studentId}/payments", method = RequestMethod.GET)
	public ResponseEntity<?> readPaymentForStudent(@PathVariable("studentId") Long studentId) {

		Student student = studentRepository.findById(studentId).orElse(null);

		if (student == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new MessageResponse("Student not found with ID " + studentId));
		} else {
			List<Payment> payments = student.getPayments();
			return ResponseEntity.status(HttpStatus.OK).body(payments);
		}
	}
}
