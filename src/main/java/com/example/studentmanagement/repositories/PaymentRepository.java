package com.example.studentmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentmanagement.models.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
	
}
