package com.example.studentmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.models.Address;
import com.example.studentmanagement.services.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> readAddresses() {
        return addressService.readaddresses();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> readAddressById(@PathVariable Long id) {
        return addressService.readAddressById(id);
    }
}
