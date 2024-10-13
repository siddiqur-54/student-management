package com.example.studentmanagement.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.models.Address;
import com.example.studentmanagement.repositories.AddressRepository;
import com.example.studentmanagement.services.AddressService;

@Service
public class AddressServiceImplementation implements AddressService{
	
	@Autowired
    private AddressRepository addressRepository;

	public ResponseEntity<?> createAddress(Address address) {
        address = addressRepository.saveAndFlush(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }
	
	public ResponseEntity<?> readaddresses() {
		List<Address> addresses = addressRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(addresses);
	}

    public ResponseEntity<?> readAddressById(Long id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address != null) {
            return ResponseEntity.status(HttpStatus.OK).body(address);
        } 
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Address not found with ID: " + id);
        }
    }
}
