package com.example.studentmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.models.Payment;
import com.example.studentmanagement.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createPayment(@RequestBody Payment payment) {
		return paymentService.createPayment(payment);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> readPayments() {
		return paymentService.readPayments();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> readPaymentById (@PathVariable("id") Long id) {
		return paymentService.readPaymentById(id);
	}
}
