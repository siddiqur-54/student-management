package com.example.studentmanagement.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.repositories.StudentRepository;
import com.example.studentmanagement.models.Address;
import com.example.studentmanagement.models.Student;
import com.example.studentmanagement.payloads.MessageResponse;
import com.example.studentmanagement.services.StudentService;


@Service
public class StudentServiceImplementation implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;
	
	public ResponseEntity<?> checkValidation(Student student) {

	    if (student.getName() == null || student.getName().trim().isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(new MessageResponse("Name cannot be empty."));
	    }

	    if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(new MessageResponse("Email cannot be empty."));
	    }

	    if (student.getAge() < 13) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(new MessageResponse("Age can't be below 13."));
	    }
	    
	    return ResponseEntity.ok().build();
	}
	
	
	@Override
    public ResponseEntity<?> createStudent(Student student) {
		
		ResponseEntity<?> validationResponse = checkValidation(student);
	    if (validationResponse.getStatusCode() != HttpStatus.OK) {
	        return validationResponse;
	    }
		
		Optional<Student> studentExsitingStudentId = studentRepository.findStudentByStudentId(student.getStudentId());
        if (studentExsitingStudentId.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Student ID already exists."));
        }
		
        Optional<Student> studentExistingEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (studentExistingEmail.isPresent()) {
            return new ResponseEntity<>("Email already Taken", HttpStatus.BAD_REQUEST);
        }
        
        student = studentRepository.saveAndFlush(student);
		return ResponseEntity.status(HttpStatus.OK).body(student);
    }
	
	
	@Override
	public ResponseEntity<?> readStudents() {
		
	    List<Student> students = studentRepository.findAll();
	    if (students.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new MessageResponse("No students found."));
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(students);
	}

	
	@Override
	public ResponseEntity<?> readStudentById(Long id) {
		
	    Student student = studentRepository.findById(id).orElse(null);
	    if (student != null) {
	        return ResponseEntity.status(HttpStatus.OK).body(student);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new MessageResponse("Student not found with ID: " + id));
	    }
	}

	
	@Override
	public ResponseEntity<?> readStudentByName(String nameForSearch) {
		
	    List<Student> students = studentRepository.findByNameContainingIgnoreCase(nameForSearch);
	    if (students.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new MessageResponse("No students found with name containing: " + nameForSearch));
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(students);
	}

	
	@Override
	public ResponseEntity<?> updateStudent(Long id, Student student) {
		
	    Student studentExisting = studentRepository.findById(id).orElse(null);
	    
	    if (studentExisting == null){
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new MessageResponse("Student not found with ID: " + id));
	    }
	    else {
	    	
	    	ResponseEntity<?> validationResponse = checkValidation(student);
	        if (validationResponse.getStatusCode() != HttpStatus.OK) {
	            return validationResponse;
	        }

	        Optional<Student> studentExistingEmail = studentRepository.findStudentByEmail(student.getEmail());
	        if (studentExistingEmail.isPresent() && !studentExistingEmail.get().getId().equals(id)) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(new MessageResponse("Email already exists."));
	        }

	        Optional<Student> studentExsitingStudentId = studentRepository.findStudentByStudentId(student.getStudentId());
	        if (studentExsitingStudentId.isPresent() && !studentExsitingStudentId.get().getId().equals(id)) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(new MessageResponse("Student ID already exists."));
	        }

	        studentExisting.setStudentId(student.getStudentId());
	        studentExisting.setName(student.getName());
	        studentExisting.setEmail(student.getEmail());
	        studentExisting.setBirthDate(student.getBirthDate());
	        studentExisting.setActive(student.getActive());
	        
	        if (student.getAddress() != null) {
	        	if (studentExisting.getAddress() != null) {
	        		Address addressExisting = studentExisting.getAddress();
	        		addressExisting.setStreet(student.getAddress().getStreet());
	        		addressExisting.setCity(student.getAddress().getCity());
	        		addressExisting.setZipcode(student.getAddress().getZipcode());
	        	}
	        	else {
	        		studentExisting.setAddress(student.getAddress());
	        	}
	        }
	        
	        Student updatedStudent = studentRepository.saveAndFlush(studentExisting);
	        return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
	    }
	}

	@Override
	public ResponseEntity<?> deleteStudent(Long id) {
		
	    Student student = studentRepository.findById(id).orElse(null);
	    if (student != null) {
	        studentRepository.deleteById(id);
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new MessageResponse("Student not found with ID: " + id));
	    }
	}
}
