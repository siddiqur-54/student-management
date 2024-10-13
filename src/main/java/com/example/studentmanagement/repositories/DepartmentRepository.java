package com.example.studentmanagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentmanagement.models.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	Optional<Department> findByCodeIgnoreCase(String code);
}
