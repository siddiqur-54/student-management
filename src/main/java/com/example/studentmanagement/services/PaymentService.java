package com.example.studentmanagement.services;

import org.springframework.http.ResponseEntity;

import com.example.studentmanagement.models.Payment;

public interface PaymentService {

	public ResponseEntity<?> createPayment(Payment payment);

	public ResponseEntity<?> readPayments();

	public ResponseEntity<?> readPaymentById(Long id);

}
