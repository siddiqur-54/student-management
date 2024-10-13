package com.example.studentmanagement.services;

import com.example.studentmanagement.models.Address;
import org.springframework.http.ResponseEntity;

public interface AddressService {

    public ResponseEntity<?> createAddress(Address address);
    
    public ResponseEntity<?> readaddresses();
    
    public ResponseEntity<?> readAddressById(Long id);
}