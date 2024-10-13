package com.example.studentmanagement.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.models.Payment;
import com.example.studentmanagement.repositories.PaymentRepository;
import com.example.studentmanagement.services.PaymentService;

@Service
public class PaymentServiceImplementation implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public ResponseEntity<?> createPayment(Payment payment) {
		payment = paymentRepository.saveAndFlush(payment);
		return ResponseEntity.status(HttpStatus.CREATED).body(payment);
	}

	public ResponseEntity<?> readPayments() {
		List<Payment> payments = paymentRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(payments);
	}

	public ResponseEntity<?> readPaymentById(Long id) {
		Payment payment = paymentRepository.findById(id).orElse(null);
		if(payment != null) {
			return ResponseEntity.status(HttpStatus.OK).body(payment);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found with ID: " + id);
		}
	}
}
