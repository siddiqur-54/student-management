package com.example.studentmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentmanagement.models.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
